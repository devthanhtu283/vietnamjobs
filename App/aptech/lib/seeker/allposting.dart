import 'dart:async';
import 'dart:convert';

import 'package:aptech/entities/posting.dart';
import 'package:aptech/models/employer_api.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/employer_details.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class AllPostingPage extends StatefulWidget {

  String? keyword;
  AllPostingPage({this.keyword});
  @override
  State<StatefulWidget> createState() {
    return AllPostingPageState();
  }
}

class AllPostingPageState extends State<AllPostingPage> {
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }

  String _selectedItem = '';
  final _formKey = GlobalKey<FormState>();
  var dateFormat = DateFormat("dd/MM/yyyy");
  var employeeAPI = EmployerAPI();
  Future<List<Posting>>? posting;
  var postingAPI = PostingAPI();

  @override
  void initState() {
    if(widget!.keyword==null)
      {
        posting = postingAPI.findAll();
      }
    else{
      posting=postingAPI.findByTitle(widget!.keyword!);
    }

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "All Posting",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: Color(0xFF22A849),
      ),
      body: FutureBuilder(
        future: posting,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: (BuildContext context, int index) {
                var item = snapshot.data![index];
                return GestureDetector(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) =>
                            JobDetailsPage(id: item.id!),
                      ),
                    );
                  },
                  child: Container(
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
                              crossAxisAlignment:
                              CrossAxisAlignment.start,
                              children: [
                                Text(
                                  convertEncoding(item.title!),
                                  style: const TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 18),
                                  maxLines: 2,
                                  overflow: TextOverflow.ellipsis,
                                ),
                                const SizedBox(height: 8),
                                Text("${convertEncoding(item.employerName!)}",
                                    style: Theme.of(context)
                                        .textTheme
                                        .caption!
                                        .copyWith(fontSize: 15)),
                                Text(
                                    " ${dateFormat.format(item!.created!)}"),
                                const SizedBox(height: 8),
                                Row(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    Text("${convertEncoding(item.wageName!)}"),
                                    Text("${convertEncoding(item.localName!)}")
                                  ].map((e) {
                                    return InkWell(
                                      onTap: () {},
                                      child: Padding(
                                        padding: const EdgeInsets.only(
                                            right: 8.0),
                                        child: e,
                                      ),
                                    );
                                  }).toList(),
                                )
                              ],
                            )),
                        Container(
                            width: 100,
                            height: 100,
                            decoration: BoxDecoration(
                                color: Colors.grey,
                                borderRadius:
                                BorderRadius.circular(8.0),
                                image: DecorationImage(
                                  fit: BoxFit.fitWidth,
                                  image: AssetImage(
                                      "assets/images/${item.employerLogo}"),
                                ))),
                      ],
                    ),
                  ),
                );
              },
            );
          } else {
            return CircularProgressIndicator();
          }
        },
      )
    );
  }
}
