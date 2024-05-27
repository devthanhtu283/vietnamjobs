import 'dart:async';
import 'dart:convert';

import 'package:aptech/entities/employer.dart';
import 'package:aptech/entities/posting.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class EmployerDetailsPage extends StatefulWidget{
  var employer = Employer();
  EmployerDetailsPage({required this.employer});

  @override
  State<StatefulWidget> createState() {
   return EmployerDetailsPageState();
  }


}

class EmployerDetailsPageState extends State<EmployerDetailsPage> {

  Future<List<Posting>>? postings;
  var postingAPI = PostingAPI();
  @override
  void initState() {
    postings = postingAPI.findByEmployerID(widget.employer.id!);
  }


  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Image(image: AssetImage("assets/images/${widget.employer.logo}"), height: 300,),
          Expanded(
            flex: 3,
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                children: [
                  RichText(text: TextSpan(
                    text: "${convertEncoding(widget.employer.name!)}",
                    style: TextStyle(color: Colors.black,fontSize: 20),

                  )),

                  const SizedBox(height: 16),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      FloatingActionButton.extended(
                        onPressed: () {},
                        heroTag: 'follow',
                        elevation: 0,
                        label: const Text("Follow"),
                        icon: const Icon(Icons.person_add_alt_1),
                      ),
                      const SizedBox(width: 16.0),
                      FloatingActionButton.extended(
                        onPressed: () {},
                        heroTag: 'mesage',
                        elevation: 0,
                        backgroundColor: Colors.red,
                        label: const Text("Message"),
                        icon: const Icon(Icons.message_rounded),
                      ),
                    ],
                  ),


                ],
              ),
            ),
          ),
         Text("Job posted", style: TextStyle(fontSize: 25),),
         Container(
           height: 350,

           child:  FutureBuilder(future: postings, builder: (context, snapshot) {
             if(snapshot.hasData){
               return ListView(
                 children: snapshot.data!.map((e) => Card(
                   elevation: 5,
                   child: ListTile(
                     title: Text(convertEncoding(e.title!,), style: TextStyle(fontSize: 18),),
                    onTap: () {
                      Navigator.push(context, MaterialPageRoute(builder: (context) => JobDetailsPage(id: e.id),));
                    },
                   ),
                 )).toList(),
               );
             } else {
               return Center(child: CircularProgressIndicator(),);
             }
           },),
         ),

        ],
      ),

    );
  }





}
