import 'package:intl/intl.dart';

class FollowDB {
  int? seekerID;
  int? employerID;
  String? employerName;
  int? postingID;
  String? postingTitle;

  FollowDB({this.seekerID, this.employerID, this.employerName, this.postingID,
    this.postingTitle});

  FollowDB.fromMap(Map<String, dynamic> map) {
    var dateFormat = DateFormat("dd/MM/yyyy");
    seekerID = map["seekerID"];
    employerID = map["employerID"];
    employerName = map["employerName"];
    postingID = map["postingID"];
    postingTitle = map["postingTitle"];
  }

  Map<String, dynamic> toMap() {
    var dateFormat = DateFormat("dd/MM/yyyy");
    return <String, dynamic>{
      "seekerID": seekerID ?? 0,
      "employerID": employerID ?? 0,
      "employerName": employerName,
      "postingID": postingID,
      "postingTitle": postingTitle,
    };
  }
}