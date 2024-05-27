import 'package:aptech/seeker/home.dart';
import 'package:aptech/seeker/noti.dart';
import 'package:aptech/seeker/noti1.dart';
import 'package:aptech/seeker/profile.dart';
import 'package:aptech/seeker/theodoi.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class DashboardPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return DashboardPageState();
  }
}

class DashboardPageState extends State<DashboardPage> {
  int _selectedIndex = 0;
  List<Widget>? pages = [HomePage(),TheoDoiPage(), NotiPage(), ProfilePage()];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: pages![_selectedIndex!],
      bottomNavigationBar: SalomonBottomBar(
          currentIndex: _selectedIndex,
          selectedItemColor: const Color(0xff6200ee),
          unselectedItemColor: const Color(0xff757575),
          onTap: (index) {
            setState(() {
              _selectedIndex = index;
            });
          },
          items: _navBarItems),
    );
  }
}

final _navBarItems = [
  SalomonBottomBarItem(
    icon: const Icon(Icons.home),
    title: const Text(
      "Home",
      style: TextStyle(fontSize: 15),
    ),
    selectedColor: Color(0xFF22A849),
  ),
  SalomonBottomBarItem(
    icon: const Icon(Icons.favorite_border),
    title: const Text("Follow", style: TextStyle(fontSize: 15)),
    selectedColor: Color(0xFF22A849),
  ),
  SalomonBottomBarItem(
    icon: const Icon(Icons.notifications),
    title: const Text("Announcement", style: TextStyle(fontSize: 15)),
    selectedColor: Color(0xFF22A849),
  ),
  SalomonBottomBarItem(
    icon: const Icon(Icons.person),
    title: const Text("Account", style: TextStyle(fontSize: 15)),
    selectedColor: Color(0xFF22A849),
  ),
];
