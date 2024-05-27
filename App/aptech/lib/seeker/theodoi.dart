import 'dart:async';
import 'dart:convert';

import 'package:aptech/entities/account.dart';
import 'package:aptech/entities/application_history.dart';
import 'package:aptech/entities/posting.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/models/application_history_api.dart';
import 'package:aptech/models/employer_api.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/employer_details.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TheoDoiPage extends StatefulWidget {

  String? keyword;
  TheoDoiPage({this.keyword});
  @override
  State<StatefulWidget> createState() {
    return TheoDoiPageState();
  }

}

class TheoDoiPageState extends State<TheoDoiPage> {
  String convertEncoding(String message) {
    // If encoding conversion is unnecessary, you can remove this method
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }

  String _selectedItem = '';
  final _formKey = GlobalKey<FormState>();
  var employeeAPI = EmployerAPI();
  Account? account;
  Seeker? seeker;
  Future<List<ApplicationHistory>>? applicationHistorys;
  var applicationhistoryAPI = ApplicationHistoryAPI();

  @override
  void initState() {
    super.initState();
    loadData();
  }

  void loadData() async {
    var shared = await SharedPreferences.getInstance();
    if (shared.getString("account") != null) {
      setState(() {
        var s = shared.getString("account");
        account = Account.fromMap(jsonDecode(s!));
        var k = shared.getString("seeker");
        seeker = Seeker.fromMap(jsonDecode(k!));
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Have applied",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: Color(0xFF22A849),
      ),
      body: FutureBuilder(
        future: applicationhistoryAPI.findBySeekerID1(seeker!.id!),
        builder: (context, snapshot) {
           if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: (BuildContext context, int index) {
                var item = snapshot.data![index];
                return Container(
                  height: 200,
                  margin: const EdgeInsets.symmetric(
                      horizontal: 16, vertical: 8.0),
                  decoration: BoxDecoration(
                      border: Border.all(
                          color: const Color(0xFFE0E0E0)),
                      borderRadius: BorderRadius.circular(8.0)),
                  padding: const EdgeInsets.all(8),
                  child: Row(
                    children: [
                      Expanded(
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              convertEncoding(item.postingTitle!) ?? 'No Name',
                              style: const TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18),
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                            ),
                            const SizedBox(height: 8),
                            Text(convertEncoding(item.employerName!) ?? 'No Title'),
                            const SizedBox(height: 8),
                            Text("Status: " + (item.result! == 0 ? "Not approved yet" : (item.result! == 1 ? "Has been approved" : "Was rejected"))),

                          ],
                        ),
                      ),
                      Container(
                        width: 100,
                        height: 100,
                        decoration: BoxDecoration(
                          color: Colors.grey,
                          borderRadius: BorderRadius.circular(8.0),
                          image: DecorationImage(
                            fit: BoxFit.fitWidth,
                            image: AssetImage(
                                "assets/images/${item.employerPhoto ?? "company1.jpg"}"),
                          ),
                        ),
                      ),
                    ],
                  ),
                );
              },
            );
          } else {
            return Center(child: Text('No Data'));
          }
        },
      ),
    );
  }
}
