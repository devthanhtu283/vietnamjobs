import 'package:intl/intl.dart';
class Seeker{
   int? id;
   int? accountID;
   String? fullName;
   String?phone;
   String? description;
   String? cvInformation;
   bool? status;
   String? email;
   double? wallet;
   String? avatar;
   Seeker({this.accountID,this.fullName,this.phone,this.description,this.cvInformation,this.status,this.email,this.wallet,this.avatar});
   Seeker.fromMap(Map<String,dynamic> map){
     id=map["id"];
     accountID=map["accountID"];
     phone=map["phone"];
     fullName=map["fullName"];
     email=map["email"];
     description=map["description"];
     cvInformation=map["cvInformation"];
     status=map["status"];
     wallet=map["wallet"];
     avatar=map["avatar"];
   }
   Map<String,dynamic> toMap(){
     return <String,dynamic>{
       "id":id ?? 0,
       "accountID":accountID,
       "fullName":fullName,
       "description":description,
       "email":email,
       "cvInformation":cvInformation,
       "status":status,
       "wallet":wallet,
       "phone":phone,
       "avatar":avatar,
     };
   }
}