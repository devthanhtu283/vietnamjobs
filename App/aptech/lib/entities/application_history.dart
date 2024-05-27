import 'package:intl/intl.dart';

class ApplicationHistory{
  int? postingID;
  int? seekerID;
  int? status;
  int? result;
  DateTime? created;
  String? employerName;
  String? postingTitle;
  String? employerPhoto;

  ApplicationHistory({this.postingID, this.seekerID, this.status, this.result,
    this.created, this.employerName, this.postingTitle,this.employerPhoto});
  ApplicationHistory.fromMap(Map<String, dynamic> map) {
    var dateFormat = DateFormat("dd/MM/yyyy");
    postingID = map["postingID"];
    seekerID = map["seekerID"];
    status = map["status"];
    result = map["result"];
    created = map["created"];
    employerName = map["employerName"];
    postingTitle = map["postingTitle"];
    employerPhoto = map["employerPhoto"];
  }

  Map<String, dynamic> toMap() {
    var dateFormat = DateFormat("dd/MM/yyyy");
    return <String, dynamic>{
      "postingID": postingID,
      "seekerID": seekerID,
      "status": status,
      "result": result,
      "created": dateFormat.format(created! ?? DateTime.now()),
      "employerName": employerName,
      "postingTitle": postingTitle,
      "employerPhoto": employerPhoto,
    };
  }
}