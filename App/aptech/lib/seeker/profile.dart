import 'dart:convert';

import 'package:aptech/seeker/doi_mat_khau.dart';
import 'package:aptech/seeker/edit_profile.dart';
import 'package:aptech/entities/account.dart';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/login.dart';
import 'package:aptech/models/seeker_api.dart';
import 'package:aptech/seeker/top_cong_ty_hang_dau.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProfilePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ProfilePageState();
  }
}

class ProfilePageState extends State<ProfilePage> {
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }
  Account? account;
  Future<Seeker>? seeker1;
  Seeker? seeker;
  @override
  void initState() {
    loadData();

  }

  void loadData() async {
    var shared = await SharedPreferences.getInstance();
    if (shared.getString("account") != null) {
      setState(() {
        var s = shared.getString("account");
        account = Account.fromMap(jsonDecode(s!));
        var k =shared.getString("seeker");
        seeker=Seeker.fromMap(jsonDecode(k!));
      });
    }
   /* var seekerModel = SeekerAPI();
    seeker =  await seekerModel.findByAccountID(account!.id!);
    seeker1 =   seekerModel.findByAccountID(account!.id!);*/
  }

  @override
  Widget build(BuildContext context) {
    var isDark = MediaQuery.of(context).platformBrightness == Brightness.dark;
    var iconColor = isDark ? Colors.primaries : Colors.amberAccent;
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Information",
          style: Theme.of(context).textTheme.headline4,
        ),
        actions: [
          IconButton(
              onPressed: () {},
              icon: Icon(isDark ? LineAwesomeIcons.sun : LineAwesomeIcons.moon))
        ],
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
                      child: Image.asset("assets/images/doraemon.jpg"),
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
                        LineAwesomeIcons.alternate_pencil,
                        size: 18.0,
                        color: Colors.white,
                      ),
                    ),
                  )
                ],
              ),
              const SizedBox(
                height: 10,
              ),
              Text(
                "${convertEncoding(seeker!.fullName!) ?? "Nguoi dung"}",
                style: Theme.of(context).textTheme.headline4,
              ),
              Text(
                "${account?.email ?? "Please log in"}",
                style: Theme.of(context).textTheme.bodyText2,
              ),
              const SizedBox(
                height: 20,
              ),
              SizedBox(
                width: 200,
                child: ElevatedButton(
                  onPressed: () async{
                    var shared = await SharedPreferences.getInstance();
                    if (shared.getString("account") != null) {
                       Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => EditProfile(seeker: seeker,),),
                      );
                    } else {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => LoginPage(),),
                      );
                    }

                  },
                  child: const Text("Edit information",
                      style: TextStyle(color: Colors.white)),
                  style: ElevatedButton.styleFrom(
                      backgroundColor: Color(0xFF22A849),
                      side: BorderSide.none,
                      shape: StadiumBorder()),
                ),
              ),
              const SizedBox(
                height: 30,
              ),

              const SizedBox(
                height: 10,
              ),

              //MENU
              ProflleMenuWidget(
                title: "Setting",
                icon: LineAwesomeIcons.cog,
                onPress: () {},
              ),
              ProflleMenuWidget(
                title: "The company is watching",
                icon: LineAwesomeIcons.wallet,
                onPress: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => TopCongTyHangDauPage(seeker: seeker,),),
                  );
                },
              ),
              ProflleMenuWidget(
                title: "Change Password",
                icon: LineAwesomeIcons.user_check,
                onPress: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => DoiMatKhauPage(account: account!,),),
                  );
                },
              ),
              const Divider(color: Colors.grey),
              const SizedBox(
                height: 10,
              ),
              ProflleMenuWidget(
                title: "Detail",
                icon: LineAwesomeIcons.info,
                onPress: () {},
              ),
              ProflleMenuWidget(
                title: "Log out",
                icon: LineAwesomeIcons.alternate_sign_out,
                onPress: () async {
                  var shared = await SharedPreferences.getInstance();
                  shared.remove("account");
                  Navigator.push(context, MaterialPageRoute(builder: (context) => LoginPage(),));
                },
                textColor: Colors.red,
                endIcon: false,
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class ProflleMenuWidget extends StatelessWidget {
  const ProflleMenuWidget({
    super.key,
    required this.title,
    required this.icon,
    required this.onPress,
    this.endIcon = true,
    this.textColor,
  });

  final String title;
  final IconData icon;
  final VoidCallback onPress;
  final bool endIcon;
  final Color? textColor;

  @override
  Widget build(BuildContext context) {
    var isDark = MediaQuery.of(context).platformBrightness == Brightness.dark;
    var iconColor = isDark ? Color(0xFF22A849) : Colors.amberAccent;
    return ListTile(
      onTap: onPress,
      leading: Container(
        width: 40,
        height: 40,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(100),
          color: Color(0xFF22A849),
        ),
        child: Icon(
          icon,
          color: Colors.white,
        ),
      ),
      title: Text(title,
          style:
              Theme.of(context).textTheme.bodyText1?.apply(color: textColor)),
      trailing: endIcon
          ? Container(
              width: 30,
              height: 30,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(100),
                color: Colors.grey.withOpacity(0.1),
              ),
              child: Icon(
                LineAwesomeIcons.angle_right,
                size: 18.0,
                color: Colors.grey,
              ),
            )
          : null,
    );
  }
}
