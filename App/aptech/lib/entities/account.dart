import 'package:intl/intl.dart';

class Account {
  int? id;
  int? typeAccountID;
  String? username;
  String? password;
  DateTime? created;
  String? email;
  bool? status;
  String? securityCode;
  double? wallet;

  Account(
      {this.id,
      this.typeAccountID,
      this.username,
      this.password,
      this.created,
      this.email,
      this.status,
      this.securityCode,
      this.wallet});

  Account.fromMap(Map<String, dynamic> map) {
    var dateFormat = DateFormat("dd/MM/yyyy");
    id = map["id"];
    typeAccountID = map["typeAccountID"];
    username = map["username"];
    password = map["password"];
    created = dateFormat.parse(map["created"]);
    email = map["email"];
    status = map["status"];
    securityCode = map["securityCode"];
    wallet = map["wallet"];
  }

  Map<String, dynamic> toMap() {
    var dateFormat = DateFormat("dd/MM/yyyy");
    return <String, dynamic>{
      "id": id ?? 0,
      "username": username,
      "typeAccountID": typeAccountID,
      "password": password,
      "created": dateFormat.format(created! ?? DateTime.now()),
      "email": email,
      "status": status,
      "securityCode": securityCode,
      "wallet": wallet,
    };
  }
}
