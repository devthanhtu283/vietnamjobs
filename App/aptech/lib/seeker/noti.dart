import 'dart:convert';
import 'dart:math';

import 'package:aptech/entities/follow_db.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/login.dart';
import 'package:aptech/models/colors.dart';
import 'package:aptech/models/follow_api.dart';
import 'package:aptech/models/note.dart';
import 'package:aptech/seeker/edit.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';


class NotiPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return NotiPageState();
  }



}

class NotiPageState extends State<NotiPage> {
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }
  Seeker? seeker;
  Future<List<FollowDB>>? follows;
  var followAPI = FollowAPI();
  List<Note> filteredNotes = [];
  bool sorted = false;
  void loadData() async{
    var shared = await SharedPreferences.getInstance();
    if (shared.getString("account") != null) {
      setState(() {
        var k =shared.getString("seeker");
        seeker=Seeker.fromMap(jsonDecode(k!));
        print(seeker!.fullName);
        follows = followAPI.listPostFollowBySeekerID(seeker!.id!);
      });


    } else {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => LoginPage(),),
      );
    }

  }
  @override
  void initState() {
    loadData();


  }


  getRandomColor() {
    Random random = Random();
    return backgroundColors[random.nextInt(backgroundColors.length)];
  }

  void onSearchTextChanged(String searchText) {
    // setState(() {
    //   filteredNotes = sampleNotes
    //       .where((note) =>
    //           note.content.toLowerCase().contains(searchText.toLowerCase()) ||
    //           note.title.toLowerCase().contains(searchText.toLowerCase()))
    //       .toList();
    // });
  }

  void deleteNote(int index) {
    // setState(() {
    //   Note note = filteredNotes[index];
    //   sampleNotes.remove(note);
    //   filteredNotes.removeAt(index);
    // });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "New post",
          style: TextStyle(color: Colors.white),
        ),
        actions: [
          IconButton(
              onPressed: () {
                // setState(() {
                //   filteredNotes = sortNotesByModifiedTime(filteredNotes);
                // });
              },
              padding: const EdgeInsets.all(0),
              icon: Container(
                width: 40,
                height: 40,
                decoration: BoxDecoration(
                    color: Colors.green[300],
                    borderRadius: BorderRadius.circular(10)),
                child: const Icon(
                  Icons.sort,
                  color: Colors.white,
                ),
              ))
        ],
        backgroundColor: Color(0xFF22A849),
      ),
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.fromLTRB(16, 0, 16, 0),
        child: Column(
          children: [

            const SizedBox(
              height: 20,
            ),
           /* TextField(
              onChanged: onSearchTextChanged,
              style: const TextStyle(fontSize: 16, color: Colors.white),
              decoration: InputDecoration(
                contentPadding: const EdgeInsets.symmetric(vertical: 12),
                hintText: "Search notes...",
                hintStyle: const TextStyle(color: Colors.white),
                prefixIcon: const Icon(
                  Icons.search,
                  color: Colors.white,
                ),
                fillColor: Colors.green[300],
                filled: true,
                focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: const BorderSide(color: Colors.transparent),
                ),
                enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: const BorderSide(color: Colors.transparent),
                ),
              ),
            ),*/
            SizedBox(height: 20,),
            Expanded(
                child:  FutureBuilder(
                  future: follows,
                  builder: (context, snapshot) {
                      if(snapshot.hasData){
                        return  ListView.builder(
                          padding: const EdgeInsets.only(top: 30),
                          itemCount: snapshot.data!.length,
                          itemBuilder: (context, index) {
                            var item = snapshot.data!;
                            return Card(
                              margin: const EdgeInsets.only(bottom: 20),
                              color: getRandomColor(),
                              elevation: 3,
                              shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(10)),
                              child: Padding(
                                padding: const EdgeInsets.all(10.0),
                                child: ListTile(
                                  onTap: ()  {
                                    Navigator.push(context, MaterialPageRoute(builder: (context) => JobDetailsPage(id: item![index].postingID),));
                                  },
                                  title: RichText(
                                    maxLines: 3,
                                    overflow: TextOverflow.ellipsis,
                                    text: TextSpan(
                                        text: '${convertEncoding(item[index].employerName!)} \n',
                                        style: const TextStyle(
                                            color: Colors.black,
                                            fontWeight: FontWeight.bold,
                                            fontSize: 18,
                                            height: 1.5),
                                        children: [
                                          TextSpan(
                                            text: convertEncoding(item[index].postingTitle!),
                                            style: const TextStyle(
                                                color: Colors.black,
                                                fontWeight: FontWeight.normal,
                                                fontSize: 14,
                                                height: 1.5),
                                          ),

                                        ]),
                                  ),

                                  trailing: IconButton(
                                    onPressed: () async {
                                      final result = await confirmDialog(context);
                                      if (result != null && result) {
                                        deleteNote(index);
                                      }
                                    },
                                    icon: const Icon(
                                      Icons.delete,
                                    ),
                                  ),
                                ),
                              ),
                            );
                          },
                        );
                      } else {
                        return Center(child: CircularProgressIndicator(),);
                      }
                  },
                ))
          ],
        ),
      ),

    );
  }

  Future<dynamic> confirmDialog(BuildContext context) {
    return showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: Colors.grey.shade900,
            icon: const Icon(
              Icons.info,
              color: Colors.grey,
            ),
            title: const Text(
              'Are you sure you want to delete? ',
              style: TextStyle(color: Colors.white),
            ),
            content: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  ElevatedButton(
                      onPressed: () {
                        Navigator.pop(context, true);
                      },
                      style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.green),
                      child: const SizedBox(
                        width: 60,
                        child: Text(
                          'Yes',
                          textAlign: TextAlign.center,
                          style: TextStyle(color: Colors.white),
                        ),
                      )),
                  ElevatedButton(
                      onPressed: () {
                        Navigator.pop(context, false);
                      },
                      style:
                          ElevatedButton.styleFrom(backgroundColor: Colors.red),
                      child: const SizedBox(
                        width: 60,
                        child: Text(
                          'No',
                          textAlign: TextAlign.center,
                          style: TextStyle(color: Colors.white),
                        ),
                      )),
                ]),
          );
        });
  }
}
