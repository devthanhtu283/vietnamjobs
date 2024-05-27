import 'dart:async';
import 'dart:convert';

import 'package:aptech/entities/employer.dart';
import 'package:aptech/entities/follow.dart';
import 'package:aptech/entities/follow_db.dart';
import 'package:aptech/entities/posting.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/login.dart';
import 'package:aptech/models/employer_api.dart';
import 'package:aptech/models/follow_api.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/employer_details.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';
import 'package:shared_preferences/shared_preferences.dart';

class NoTi1Page extends StatefulWidget {
  NoTi1Page();
  @override
  State<StatefulWidget> createState() {
    return NoTi1PageState();
  }
}

class NoTi1PageState extends State<NoTi1Page> {
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
  Future<List<Employer>>? employer;
  var postingAPI = PostingAPI();
  Future<List<FollowDB>>? follows;
  var followAPI = FollowAPI();
  Seeker? seeker;

  @override
  void initState() {
    loadData();
  }

void loadData() async {


  var shared = await SharedPreferences.getInstance();
  if (shared.getString("seeker") != null) {
    setState(() {
      var k =shared.getString("seeker");
      seeker=Seeker.fromMap(jsonDecode(k!));
      follows = followAPI.listPostFollowBySeekerID(seeker!.id!);
    });
  }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(
            "New Post",
            style: TextStyle(color: Colors.white),
          ),
          backgroundColor: Color(0xFF22A849),
        ),
        body: FutureBuilder(
          future: follows,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(child: CircularProgressIndicator());
            } else if (snapshot.hasError) {
              return Center(child: Text('Error: ${snapshot.error}'));
            } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
              return Center(child: Text('No data available'));
            } else {
              return ListView.builder(
                itemCount: snapshot.data!.length,
                itemBuilder: (context, index) {
                  var item = snapshot.data![index];
                  return Text(item.postingTitle!);
                },
              );
            }
          },
        ),
    );
  }
}