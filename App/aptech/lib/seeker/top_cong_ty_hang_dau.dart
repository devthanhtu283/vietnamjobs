import 'dart:convert';
import 'dart:io';

import 'package:aptech/entities/follow.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/models/follow_api.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';

//SỬ DỤNG THEO DẠNG STAREFULL
class TopCongTyHangDauPage extends StatefulWidget {
  //GÁN ĐỂ LẤY DỮ LIỆU NGƯỜI TA NHẬP TRONG FORM
  Seeker? seeker;
  TopCongTyHangDauPage({required this.seeker});

  @override
  State<StatefulWidget> createState() {
    return TopCongTyHangDauPageState();
  }
//SỬ DỤNG THEO DẠNG STAREFULL
}

class TopCongTyHangDauPageState extends State<TopCongTyHangDauPage> {
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }
  var followAPI=FollowAPI();
  Future<List<Follow>>?follows;
  Future<Follow>?follows1;
  @override
  void initState() {
    print("id: ${widget.seeker!.id!}");
    follows1=followAPI.findBySeekerId1(widget.seeker!.id!);
    follows = followAPI.findBySeekerId(widget.seeker!.id!);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(

        title: Text(
          "The company is watching",
          style: Theme.of(context).textTheme.headline4,
        ),
        actions: [
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              // Xử lý sự kiện khi người dùng nhấn nút tìm kiếm
            },
          ),
        ],
      ),
      body: FutureBuilder(
        future: follows,
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
                            crossAxisAlignment:
                            CrossAxisAlignment.start,
                            children: [

                              const SizedBox(height: 8),
                              Text("${convertEncoding(item.employerName!)}",
                                  style: Theme.of(context)
                                      .textTheme
                                      .caption!
                                      .copyWith(fontSize: 15)),

                              const SizedBox(height: 8),

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
