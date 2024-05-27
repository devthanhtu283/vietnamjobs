import 'dart:async';
import 'dart:convert';
import 'dart:ui';

import 'package:aptech/entities/application_history.dart';
import 'package:aptech/entities/posting.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/models/application_history_api.dart';
import 'package:aptech/models/colors.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/constans/colors.dart';

// import 'package:aptech/view/screen/taps/profile_tap.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:iconly/iconly.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';
// import 'package:iconly/iconly.dart';

class JobDetailsPage extends StatefulWidget {
  int? id;

  JobDetailsPage({required this.id});

  @override
  State<StatefulWidget> createState() {
    return JobDetailsPageState();
  }
}

class JobDetailsPageState extends State<JobDetailsPage> {
  Future<Posting>? posting;
  var postingAPI = PostingAPI();

  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }

  @override
  void initState() {
    print(widget.id);
    posting = postingAPI.findById(widget!.id!);
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
      body: Stack(
        children: [
          SizedBox(
            width: double.infinity,
            child: FutureBuilder(
              builder: (context, snapshot) {
                return Image.asset(
                  "assets/images/${snapshot.data!.employerLogo!}",
                  height: 300,
                  width: 300,
                );
              },
              future: posting,
            ),
          ),
          buttonArrow(context),
          scroll(),
        ],
      ),
    ));
  }

  buttonArrow(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: InkWell(
        onTap: () {
          Navigator.pop(context);
        },
        child: Container(
          clipBehavior: Clip.hardEdge,
          height: 55,
          width: 55,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(25),
          ),
          child: BackdropFilter(
            filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
            child: Container(
              height: 55,
              width: 55,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(25),
              ),
              child: const Icon(
                Icons.arrow_back_ios,
                size: 20,
                color: Colors.white,
              ),
            ),
          ),
        ),
      ),
    );
  }

  scroll() {
    return FutureBuilder(
      future: posting,
      builder: (context, snapshot) {
        return DraggableScrollableSheet(
            initialChildSize: 0.6,
            maxChildSize: 1.0,
            minChildSize: 0.6,
            builder: (context, scrollController) {
              return Container(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                clipBehavior: Clip.hardEdge,
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                      topLeft: const Radius.circular(20),
                      topRight: const Radius.circular(20)),
                ),
                child: SingleChildScrollView(
                  controller: scrollController,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(top: 10, bottom: 25),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                              height: 5,
                              width: 35,
                              color: Colors.black12,
                            ),
                          ],
                        ),
                      ),
                      Text(
                        "${convertEncoding(snapshot.data!.title!)}",
                        style: Theme.of(context).textTheme.headline2,
                      ),
                      const SizedBox(
                        height: 10,
                      ),
                      Text(
                        "Công ty ",
                        style: Theme.of(context)
                            .textTheme
                            .bodyText2!
                            .copyWith(color: Colors.black, fontSize: 20),
                      ),
                      const SizedBox(
                        height: 15,
                      ),
                      Row(
                        children: [
                          Image(
                            image: AssetImage(
                                "assets/images/${snapshot.data!.employerLogo}"),
                            height: 50,
                            width: 50,
                          ),
                          Container(
                            child: Text(
                              "  ${convertEncoding(snapshot.data!.employerName!)}",
                              overflow: TextOverflow.ellipsis,
                              maxLines: 3,
                              style: Theme.of(context)
                                  .textTheme
                                  .headline3!
                                  .copyWith(color: mainText, fontSize: 20),
                            ),
                            width: 270,
                          ),
                        ],
                      ),
                      Padding(padding: EdgeInsets.only(left: 100), child: TextButton(
                        onPressed: () async{
                          var dateFormat = DateFormat("dd/MM/yyyy");
                          var shared = await SharedPreferences.getInstance();
                          var k =shared.getString("seeker");
                          var seeker=Seeker.fromMap(jsonDecode(k!));
                              var application = ApplicationHistory(
                                postingID: widget.id,
                                seekerID: seeker.id,
                                status: 0,
                                result: 0,
                                created: DateTime.now()
                              );

                              var applicationAPI = ApplicationHistoryAPI();
                              if(await applicationAPI.create(application)){
                                showDialog(
                                    context: context,
                                    builder: (context) {
                                      return AlertDialog(
                                        title: Text("Status"),
                                        content: Text("Successful application"),
                                      );
                                    });
                              } else {
                                showDialog(
                                    context: context,
                                    builder: (context) {
                                      return AlertDialog(
                                        title: Text("Status"),
                                        content: Text("Application failed"),
                                      );
                                    });
                              }
                        },
                        child: Text("Recruitment", style: TextStyle(color: Colors.white, fontSize: 20),),
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all<Color>(Color(0xFF22A849)), // Màu nền
                        ),

                      ),),
                      const Padding(
                        padding: EdgeInsets.symmetric(vertical: 15),
                        child: Divider(
                          height: 4,
                        ),
                      ),
                      Text(
                        "Job description",
                        style: Theme.of(context)
                            .textTheme
                            .bodyText2!
                            .copyWith(fontSize: 35),
                      ),
                      const SizedBox(
                        height: 10,
                      ),
                      Text(
                        '${convertEncoding(snapshot.data!.description!)}',
                        style: Theme.of(context)
                            .textTheme
                            .bodyText2!
                            .copyWith(color: Colors.black, fontSize: 30),
                      ),
                      const Padding(
                        padding: EdgeInsets.symmetric(vertical: 15),
                        child: Divider(
                          height: 4,
                        ),
                      ),
                      Text(
                        "General information",
                        style: Theme.of(context)
                            .textTheme
                            .headline1!
                            .copyWith(fontSize: 25),
                      ),
                      const SizedBox(
                        height: 10,
                      ),
                      ListView.builder(
                        physics: NeverScrollableScrollPhysics(),
                        shrinkWrap: true,
                        itemCount: 1,
                        itemBuilder: (context, index) {
                          var dateFormat = DateFormat("dd/MM/yyyy");
                          return Column(
                            children: [
                              ingredients(context, "Experience ",
                                  "${convertEncoding(snapshot.data!.expName!)}"),
                              ingredients(context, "Hình thức",
                                  "${convertEncoding(snapshot.data!.typeName!)}"),
                              ingredients(context, "Number of recruits",
                                  "${snapshot.data!.quantity}"),
                              ingredients(context, "Sex",
                                  "${convertEncoding(snapshot.data!.gender!)}"),
                              ingredients(context, "Application submission deadline",
                                  "${dateFormat.format(snapshot.data!.deadline!)}"),
                              ingredients(context, "Wage",
                                  "${convertEncoding(snapshot.data!.wageName!)}")
                            ],
                          );
                        },
                      ),
                      // const Padding(
                      //   padding: EdgeInsets.symmetric(vertical: 15),
                      //   child: Divider(
                      //     height: 4,
                      //   ),
                      // ),
                      // Text(
                      //   "Steps",
                      //   style: Theme.of(context).textTheme.headline1,
                      // ),
                      // const SizedBox(
                      //   height: 10,
                      // ),
                      // ListView.builder(
                      //   physics: NeverScrollableScrollPhysics(),
                      //   shrinkWrap: true,
                      //   itemCount: 3,
                      //   itemBuilder: (context, index) => steps(context, index),
                      // ),
                    ],
                  ),
                ),
              );
            });
      },
    );
  }

  ingredients(BuildContext context, String text1, String text2) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 10),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          const CircleAvatar(
            radius: 10,
            backgroundColor: Color(0xFFE3FFF8),
            child: Icon(
              Icons.done,
              size: 25,
              color: primary,
            ),
          ),
          const SizedBox(
            width: 15,
          ),
          Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              const SizedBox(
                height: 15,
              ),
              Text(
                text1,
                style: Theme.of(context).textTheme.bodyText2!.copyWith(fontSize: 20),
              ),
              Text(
                text2,
                style: Theme.of(context).textTheme.bodyText2,
              ),
            ],
          )
        ],
      ),
    );
  }

  steps(BuildContext context, int index) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          CircleAvatar(
            backgroundColor: mainText,
            radius: 12,
            child: Text("${index + 1}"),
          ),
          Column(
            children: [
              SizedBox(
                width: 270,
                child: Text(
                  "Your recipe has been uploaded, you can see it on your profile. Your recipe has been uploaded, you can see it on your",
                  maxLines: 3,
                  style: Theme.of(context)
                      .textTheme
                      .bodyText2!
                      .copyWith(color: mainText),
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              Image.asset(
                "assets/imges/Rectangle 219.png",
                height: 155,
                width: 270,
              )
            ],
          )
        ],
      ),
    );
  }
}
