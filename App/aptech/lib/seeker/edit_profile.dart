import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:ui';

import 'package:aptech/entities/account.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/models/seeker_api.dart';
import 'package:aptech/seeker/profile.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';
class EditProfile extends StatefulWidget{
  Seeker? seeker;
  EditProfile({required this.seeker});
  @override
  State<StatefulWidget> createState() {
    return EditProfileState();
  }

}
class EditProfileState extends State<EditProfile>{
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }
  var email = TextEditingController(text: "");
  var fullname = TextEditingController(text: "");
  var phone = TextEditingController(text: "");
  var description = TextEditingController(text: "");
  var wallet = TextEditingController(text: "");
  var cvInfomation = TextEditingController(text: "");
  var avatar = TextEditingController(text: "");
  var seekerModel = SeekerAPI();
  int? accountID;
  String? path;
  @override
  void initState(){
    fullname.text = convertEncoding(widget.seeker!.fullName!);
    phone.text = widget.seeker!.phone!;
    description.text = widget.seeker!.description!;
    cvInfomation.text = widget.seeker!.cvInformation!;
    avatar.text = widget.seeker!.avatar!;
  }

  @override
  Widget build(BuildContext context) {


    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Edit Profile",
          style: Theme.of(context).textTheme.headline4,
        ),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(15),
          child: Column(
            children: [
              Stack(
                children: [
                  SizedBox(
                    width: 120,
                    height: 120,
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(100),
                      child: Image.asset(
                        "assets/images/${widget.seeker!.avatar!}",
                      ),
                    ),
                  ),
                  Positioned(
                    bottom: 0,
                    right: 10,
                    child: Container(
                      width: 30,
                      height: 30,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        color: Color(0xFF22A849)
                      ),
                      child: Align(
                        alignment: Alignment.center,
                        child: IconButton(
                          onPressed:  () async {
                            FilePickerResult? filePickerResult = await FilePicker.platform.pickFiles();
                            if (filePickerResult != null && filePickerResult.files.isNotEmpty) {
                              PlatformFile platformFile = filePickerResult.files.first;
                              var file = File(platformFile.path!);

                              // Lấy tên của tệp tin
                              String fileName = platformFile.name;

                              // Gán tên tệp tin vào controller của TextFormField
                              avatar.text = fileName;
                            } else {
                              // Xử lý trường hợp không có tệp tin được chọn
                              // Ví dụ: Hiển thị thông báo cho người dùng
                              showDialog(
                                context: context,
                                builder: (context) {
                                  return AlertDialog(
                                    title: Text("Notification"),
                                    content: Text("No files selected."),
                                    actions: <Widget>[

                                    ],
                                  );
                                },
                              );
                            }
                          },
                          icon: Icon(
                            LineAwesomeIcons.pen,
                            size: 20,
                            color: Colors.white,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(
                height: 50,
              ),
              Form(
                child: Column(
                  children: [
                    TextFormField(
                      controller: fullname,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      decoration: const InputDecoration(
                        labelText: 'Full Name',
                        hintText: 'Enter your full name',
                        prefixIcon: Icon(Icons.person),
                        border: OutlineInputBorder(),
                      ),
                    ),


                    SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: phone,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      decoration: const InputDecoration(
                        labelText: 'Phone number',
                        hintText: 'Enter your phone number',
                        prefixIcon: Icon(Icons.phone),
                        border: OutlineInputBorder(),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: description,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      decoration: const InputDecoration(
                        labelText: 'Describe',
                        hintText: 'Enter a description',
                        prefixIcon: Icon(Icons.description),
                        border: OutlineInputBorder(),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    SizedBox(
                      width: double.infinity,
                      child: Row(
                        children: [
                          Expanded(
                            child: TextFormField(
                              controller: cvInfomation,
                              readOnly: true, // Không cho phép người dùng chỉnh sửa nội dung
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(right: 20, left: 20),
                          ),
                          ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              shape: StadiumBorder(),
                              side: BorderSide.none,
                              backgroundColor: Color(0xFF22A849),
                            ),
                            child: const Padding(
                              padding: EdgeInsets.all(10.0),
                              child: Icon(Icons.file_copy_rounded),
                            ),
                            onPressed: () async {
                              FilePickerResult? filePickerResult = await FilePicker.platform.pickFiles();
                              if (filePickerResult != null) {
                                PlatformFile platformFile = filePickerResult.files[0];
                                var file = File(platformFile.path!);

                                // Lấy tên của tệp tin
                                String fileName = platformFile.name;

                                // Gán tên tệp tin vào controller của TextFormField
                                cvInfomation.text = fileName;
                              }
                            },
                          ),
                        ],
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    SizedBox(
                      width: double.infinity,
                      child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            shape: StadiumBorder(),
                            side: BorderSide.none,
                            backgroundColor: Color(0xFF22A849)),
                        child: const Padding(
                          padding: EdgeInsets.all(10.0),
                          child: Text(
                            'Edit',
                            style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              color: Colors.white,
                            ),
                          ),
                        ),
                        onPressed: ()  async{

                          widget.seeker!.avatar=avatar.text;
                          widget.seeker!.fullName=fullname.text;
                          widget.seeker!.description=description.text;
                          widget.seeker!.phone=phone.text;
                          widget.seeker!.email=email.text;
                          widget.seeker!.accountID=widget.seeker!.accountID!;
                          widget.seeker!.status=true;
                          widget.seeker!.cvInformation=cvInfomation.text;
                          var seekerAPI = SeekerAPI();
                          //CHỜ ĐẾN KHI HÀM XỬ XONG
                          if (await seekerAPI.update(widget.seeker!)) {
                          /*showDialog(
          context: context,
          builder: (context) {
            return AlertDialog(
              title: Text("Add Contact"),
              content: Text("Success"),
            );
          });*/

                          setState(() {
                          seekerAPI.findByAccountID(widget.seeker!.accountID!);
                          });
                          showDialog(
                              context: context,
                              builder: (context) {
                                return AlertDialog(
                                  title: Text("Change Profile Information"),
                                  content: Text("Success"),
                                );
                              }
                          ).then((_) {
                            // Sau khi dialog đóng lại, thực hiện điều hướng đến trang mới
                            Navigator.push(
                              context,
                              MaterialPageRoute(builder: (context) => ProfilePage()),
                            );
                          });

                          } else {
                          showDialog(
                          context: context,
                          builder: (context) {
                          return AlertDialog(
                          title: Text("Edit Contact"),
                          content: Text("Failed"),
                          );
                          });
                          }
                        },
                      ),
                    ),

                    SizedBox(
                      height: 20,
                    ),

                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
