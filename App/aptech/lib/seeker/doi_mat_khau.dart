import 'dart:ffi';
import 'dart:ui';

import 'package:aptech/seeker/dashboard.dart';
import 'package:aptech/entities/account.dart';
import 'package:aptech/models/account_api.dart';
import 'package:flutter/material.dart';
import 'package:bcrypt/bcrypt.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/widgets.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';

class DoiMatKhauPage extends StatefulWidget {
  Account? account;

  DoiMatKhauPage({required this.account});

  @override
  State<StatefulWidget> createState() {
    return DoiMatKhauPageState();
  }
}

class DoiMatKhauPageState extends State<DoiMatKhauPage> {
  var email = TextEditingController(text: "");
  var matkhau = TextEditingController(text: "");
  var matkhaumoi = TextEditingController(text: "");
  var nhaplaimatkhaumoi = TextEditingController(text: "");
  var accountAPI = AccountAPI();

  @override
  void initState() {
    email.text = widget.account!.email!;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () => {},
          icon: Icon(LineAwesomeIcons.angle_left),
        ),
        title: Text(
          "Change Password",
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
                        "assets/images/doraemon.jpg",
                      ),
                    ),
                  ),
                  Positioned(
                    bottom: 0,
                    right: 0,
                    child: Container(
                      width: 30,
                      height: 30,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        color: Color(0xFF22A849),
                      ),
                      child: Icon(
                        LineAwesomeIcons.camera,
                        size: 20,
                        color: Colors.white,
                      ),
                    ),
                  )
                ],
              ),
              const SizedBox(
                height: 50,
              ),
              Form(
                child: Column(
                  children: [
                    TextFormField(
                      controller: email,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      decoration: const InputDecoration(
                        labelText: 'FullName',
                        hintText: 'Enter your full name',
                        prefixIcon: Icon(Icons.person),
                        border: OutlineInputBorder(),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: matkhau,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      obscureText: true, // Đặt thành true để ẩn mật khẩu
                      decoration: const InputDecoration(
                        labelText: 'current password',
                        hintText: 'Enter current password',
                        prefixIcon: Icon(Icons.lock),
                        // Thay Icon với biểu tượng mật khẩu
                        border: OutlineInputBorder(),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: matkhaumoi,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      obscureText: true, // Đặt thành true để ẩn mật khẩu
                      decoration: const InputDecoration(
                        labelText: 'A new password',
                        hintText: 'Enter your new password',
                        prefixIcon: Icon(Icons.lock),
                        // Thay Icon với biểu tượng mật khẩu
                        border: OutlineInputBorder(),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: nhaplaimatkhaumoi,
                      validator: (value) {
                        // add email validation
                        if (value == null || value.isEmpty) {
                          return 'Cannot be left blank';
                        }
                        return null;
                      },
                      obscureText: true, // Đặt thành true để ẩn mật khẩu
                      decoration: const InputDecoration(
                        labelText: 'A new password',
                        hintText: 'Enter a new password',
                        prefixIcon: Icon(Icons.lock),
                        // Thay Icon với biểu tượng mật khẩu
                        border: OutlineInputBorder(),
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
                        onPressed: () => save(),
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

  void save() async{
    print(matkhau.text);
    print(widget!.account!.password!);

    print(BCrypt.checkpw(matkhau.text, widget!.account!.password!));
    if (BCrypt.checkpw(matkhau.text, widget!.account!.password!)) {
      if (matkhaumoi.text == nhaplaimatkhaumoi.text) {
        var passwordHash = BCrypt.hashpw(matkhaumoi.text, BCrypt.gensalt());
            widget!.account!.password=passwordHash.toString();
            if(await accountAPI.update(widget.account!)){
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => DashboardPage(),),
              );
              print(passwordHash);
            }
      } else {
        print("The entered password does not match");
        showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: Text("Change Password"),
                content: Text("The entered password does not match"),
              );
            });
      }
    } else {
      showDialog(
          context: context,
          builder: (context) {
            return AlertDialog(
              title: Text("Change Password"),
              content: Text("wrong password"),
            );
          });
    }
  }
}
