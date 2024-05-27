import 'package:intl/intl.dart';

class Follow {
  int? id;
  String? employerName;
  String? seekerName;
  bool? status;
  String? employerLogo;
  String? description;

  Follow({
    this.employerName,
    this.seekerName,
    this.status,
    this.employerLogo,
    this.description,
  });

  Follow.fromMap(Map<String, dynamic> map) {
    var dateFormat = DateFormat("dd/MM/yyyy");
    id = map["id"];
    employerName = map["employerName"];
    seekerName = map["seekerName"];
    status = map["status"];
    employerLogo = map["employerLogo"];
    description = map["description"];
  }

  Map<String, dynamic> toMap() {
    var dateFormat = DateFormat("dd/MM/yyyy");
    return <String, dynamic>{
      "id": id ?? 0,
      "employerName": employerName,
      "seekerName": seekerName,
      "status": status,
      "employerLogo": employerLogo,
      "description": description,
    };
  }
}
