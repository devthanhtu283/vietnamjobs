import 'dart:async';
import 'dart:convert';

import 'package:aptech/entities/account.dart';
import 'package:aptech/entities/employer.dart';
import 'package:aptech/entities/posting.dart';
import 'package:aptech/models/employer_api.dart';
import 'package:aptech/models/posting_api.dart';
import 'package:aptech/seeker/allemployer.dart';
import 'package:aptech/seeker/allposting.dart';
import 'package:aptech/seeker/employer_details.dart';
import 'package:aptech/seeker/job_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';
import 'package:shared_preferences/shared_preferences.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  String convertEncoding(String message) {
    // Chuyển đổi từ mã hóa ISO-8859-1 sang UTF-8
    List<int> bytes = latin1.encode(message);
    String utf8Message = utf8.decode(bytes);
    return utf8Message;
  }

  var postingModel = PostingAPI();
  Future<List<Posting>>? posting;
  String _selectedItem = '';
  final _formKey = GlobalKey<FormState>();
  var dateFormat = DateFormat("dd/MM/yyyy");
  var employeeAPI = EmployerAPI();
  Future<List<Employer>>? employers;
  var keyword = TextEditingController(text: "");
  Account? account;
  @override
  void initState() {
    posting = postingModel.findAll();
    employers = employeeAPI.findAll();
    loadData();
  }
  void loadData() async {
    var shared = await SharedPreferences.getInstance();
    if (shared.getString("account") != null) {
      setState(() {
        var s = shared.getString("account");
        account = Account.fromMap(jsonDecode(s!));
      });
    }
    /* var seekerModel = SeekerAPI();
    seeker =  await seekerModel.findByAccountID(account!.id!);
    seeker1 =   seekerModel.findByAccountID(account!.id!);*/
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Home",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: Color(0xFF22A849),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              color: Color(0xFF22A849),
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 25.0),
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              "Hi ${convertEncoding(account!.username!) ?? "Nguoi dung"}",
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 24,
                                  fontWeight: FontWeight.bold),
                            ),
                            SizedBox(
                              height: 8,
                            ),
                            Text(
                              '${dateFormat.format(DateTime.now())}',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold),
                            )
                          ],
                        ),
                        Container(
                            decoration: BoxDecoration(
                              color: Colors.green[300],
                              borderRadius: BorderRadius.circular(12),
                            ),
                            padding: EdgeInsets.all(12),
                            child: GestureDetector(
                              onTap: () {
                                showDialog(
                                  context: context,
                                  builder: (context) {
                                    return Dialog(
                                      //TỰ CUSTOM GIAO DIỆN
                                      child: Container(
                                        child: Padding(
                                          //HẢY CÁCH TOÀN BỘ LÀ 10
                                          padding: EdgeInsets.all(10),
                                          child: Column(
                                            mainAxisSize: MainAxisSize.min,
                                            children: [
                                              TextFormField(
                                                controller: keyword,
                                                decoration: InputDecoration(
                                                  hintText:
                                                      "Job search....",
                                                ),
                                              ),
                                              Container(
                                                margin:
                                                    EdgeInsets.only(bottom: 5),
                                              ),
                                              Row(
                                                children: [
                                                  Expanded(
                                                    child: ElevatedButton(
                                                      child: Text("Search"),
                                                      onPressed: () => {
                                                        print(
                                                            "${keyword.text}"),
                                                      Navigator.push(
                                                      context,
                                                      MaterialPageRoute(
                                                      builder: (context) => AllPostingPage(keyword: keyword.text,),
                                                      ),
                                                      ),
                                                      },
                                                    ),
                                                  ),
                                                ],
                                              )
                                            ],
                                          ),
                                        ),
                                      ),
                                    );
                                  },
                                );
                              },
                              child: Icon(
                                Icons.search,
                                color: Colors.white,
                              ),
                            ))
                      ],
                    ),
                    SizedBox(
                      height: 16,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        //bad
                        Column(
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: Colors.green[300],
                                borderRadius: BorderRadius.circular(12),
                              ),
                              padding: EdgeInsets.all(16),
                              child: Column(
                                children: [
                                  Center(
                                    child: Icon(
                                      Icons.home_work_rounded,
                                      color: Colors.white,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            SizedBox(
                              height: 8,
                            ),
                            Center(
                              child: Text(
                                "Company",
                                style: TextStyle(color: Colors.white),
                              ),
                            )
                          ],
                        ),
                        Column(
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: Colors.green[300],
                                borderRadius: BorderRadius.circular(12),
                              ),
                              padding: EdgeInsets.all(16),
                              child: Column(
                                children: [
                                  Center(
                                    child: Icon(
                                      Icons.headphones,
                                      color: Colors.white,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            SizedBox(
                              height: 8,
                            ),
                            Center(
                              child: Text(
                                "Podcast",
                                style: TextStyle(color: Colors.white),
                              ),
                            )
                          ],
                        ),
                        Column(
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: Colors.green[300],
                                borderRadius: BorderRadius.circular(12),
                              ),
                              padding: EdgeInsets.all(16),
                              child: Column(
                                children: [
                                  Center(
                                    child: Icon(
                                      Icons.newspaper,
                                      color: Colors.white,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            SizedBox(
                              height: 8,
                            ),
                            Center(
                              child: Text(
                                "Blog",
                                style: TextStyle(color: Colors.white),
                              ),
                            )
                          ],
                        ),
                        Column(
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: Colors.green[300],
                                borderRadius: BorderRadius.circular(12),
                              ),
                              padding: EdgeInsets.all(16),
                              child: Column(
                                children: [
                                  Center(
                                    child: Icon(
                                      Icons.settings,
                                      color: Colors.white,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            SizedBox(
                              height: 8,
                            ),
                            Center(
                              child: Text(
                                "Tool",
                                style: TextStyle(color: Colors.white),
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                    SizedBox(
                      height: 16,
                    ),
                  ],
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(top: 5, bottom: 5),
            ),
            Row(
              children: [
                Container(
                  margin: EdgeInsets.only(left: 30),
                ),
                Text("Best Jobs       ", style: TextStyle(fontSize: 18),),
                Container(
                  margin: EdgeInsets.only(right: 70, left: 70),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => AllPostingPage(),
                      ),
                    );
                  },
                  child: Text(
                    "View all",
                    style: TextStyle(color: Colors.green),
                  ),
                ),
              ],
            ),
            Container(
                constraints: const BoxConstraints(maxWidth: 500),
                height: 400,
                padding: EdgeInsets.only(right: 10, left: 10),
                child: FutureBuilder(
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
                                      Text(
                                          "${convertEncoding(item.employerName!)}",
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
                                          Text(
                                              "${convertEncoding(item.wageName!)}"),
                                          Text(
                                              "${convertEncoding(item.localName!)}")
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
                )),
            Row(
              children: [
                Container(
                  margin: EdgeInsets.only(left: 30),
                ),
                Text("Best Company", style: TextStyle(fontSize: 18),),
                Container(
                  margin: EdgeInsets.only(right: 70, left: 70),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => AllEmployerPage(),
                      ),
                    );
                  },
                  child: Text(
                    "View all",
                    style: TextStyle(color: Colors.green),
                  ),
                ),
              ],
            ),
            Container(
              height: 500,
              padding: EdgeInsets.only(left: 20, right: 20),
              child: FutureBuilder(
                future: employers,
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
                                    EmployerDetailsPage(employer: item,),
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
                                    child: SingleChildScrollView(
                                      child: Column(
                                        mainAxisAlignment: MainAxisAlignment.center,
                                        crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                        children: [
                                          Text(
                                            convertEncoding(item.name!),
                                            style: const TextStyle(
                                                fontWeight: FontWeight.bold,
                                                fontSize: 18),
                                            maxLines: 2,
                                            overflow: TextOverflow.ellipsis,
                                          ),
                                          const SizedBox(height: 8),
                                          Wrap(
                                            runSpacing: 8.0, // Khoảng cách giữa các dòng
                                            children: [
                                              Text("${convertEncoding(item.description!)}"),
                                              ...[].map((e) {
                                                return InkWell(
                                                  onTap: () {},
                                                  child: Padding(
                                                    padding: const EdgeInsets.only(right: 8.0),
                                                    child: e,
                                                  ),
                                                );
                                              }).toList(),
                                            ],
                                          ),
                                          Wrap(
                                            runSpacing: 8.0, // Khoảng cách giữa các dòng
                                            children: [
                                              Text("${convertEncoding(item.address!)}"),
                                              ...[].map((e) {
                                                return InkWell(
                                                  onTap: () {},
                                                  child: Padding(
                                                    padding: const EdgeInsets.only(right: 8.0),
                                                    child: e,
                                                  ),
                                                );
                                              }).toList(),
                                            ],
                                          ),
                                          const SizedBox(height: 8),
                                          Wrap(
                                            runSpacing: 8.0, // Khoảng cách giữa các dòng
                                            children: [
                                              Text("${convertEncoding(item.scale!)}"),
                                              ...[].map((e) {
                                                return InkWell(
                                                  onTap: () {},
                                                  child: Padding(
                                                    padding: const EdgeInsets.only(right: 8.0),
                                                    child: e,
                                                  ),
                                                );
                                              }).toList(),
                                            ],
                                          ),
                                        ],
                                      ),
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
                                              "assets/images/${item.logo}"),
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
              ),
            )
          ],
        ),
      ),
    );
  }
}

class AutocompleteTextField extends StatefulWidget {
  final List<String> items;
  final Function(String) onItemSelect;
  final InputDecoration? decoration;
  final String? Function(String?)? validator;

  const AutocompleteTextField(
      {Key? key,
      required this.items,
      required this.onItemSelect,
      this.decoration,
      this.validator})
      : super(key: key);

  @override
  State<AutocompleteTextField> createState() => _AutocompleteTextFieldState();
}

class _AutocompleteTextFieldState extends State<AutocompleteTextField> {
  final FocusNode _focusNode = FocusNode();
  late OverlayEntry _overlayEntry;
  final LayerLink _layerLink = LayerLink();
  late List<String> _filteredItems;

  final TextEditingController _controller = TextEditingController();

  @override
  void initState() {
    super.initState();
    _filteredItems = widget.items;
    _focusNode.addListener(() {
      if (_focusNode.hasFocus) {
        _overlayEntry = _createOverlayEntry();
        Overlay.of(context)?.insert(_overlayEntry);
      } else {
        _overlayEntry.remove();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return CompositedTransformTarget(
      link: _layerLink,
      child: Column(
        children: [
          TextFormField(
            controller: _controller,
            focusNode: _focusNode,
            onChanged: _onFieldChange,
            decoration: widget.decoration,
            validator: widget.validator,
          ),
          ElevatedButton(
            onPressed: () {
              print("${_controller.text}");
            },
            child: Text("Tìm kiếm"),
          ),
        ],
      ),
    );
  }

  void _onFieldChange(String val) {
    setState(() {
      if (val == '') {
        _filteredItems = widget.items;
      } else {
        _filteredItems = widget.items
            .where(
                (element) => element.toLowerCase().contains(val.toLowerCase()))
            .toList();
      }
    });
  }

  OverlayEntry _createOverlayEntry() {
    RenderBox renderBox = context.findRenderObject() as RenderBox;
    var size = renderBox.size;

    return OverlayEntry(
        builder: (context) => Positioned(
              width: size.width,
              child: CompositedTransformFollower(
                link: _layerLink,
                showWhenUnlinked: false,
                offset: Offset(0.0, size.height + 5.0),
                child: Material(
                  elevation: 4.0,
                  child: Container(
                    constraints: const BoxConstraints(maxHeight: 400),
                    child: ListView.builder(
                      itemCount: _filteredItems.length,
                      itemBuilder: (BuildContext context, int index) {
                        final item = _filteredItems[index];
                        return ListTile(
                          title: Text(item),
                          onTap: () {
                            _controller.text = item;
                            _focusNode.unfocus();
                            widget.onItemSelect(item);
                            print("${_controller.text}");
                          },
                        );
                      },
                    ),
                  ),
                ),
              ),
            ));
  }
}
