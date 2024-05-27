import 'package:intl/intl.dart';

class Posting{
   int? id;
   String? employerName;
   String? employerLogo;
   String? title;
   String? description;
   DateTime? created;
   DateTime? deadline;
   String? gender;
   int? quantity;
   String? wageName;
   String? categoryName;
   String? localName;
   String? rankName;
   String? typeName;
   String? expName;
   bool? status;
   Posting({this.employerName,this.title,this.description,this.created,this.deadline,this.gender,this.quantity,this.wageName,this.categoryName,this.localName,this.rankName,this.typeName,this.expName,this.status, this.employerLogo});
   Posting.fromMap(Map<String,dynamic> map){
     var dateFormat=DateFormat("dd/MM/yyyy");
     id=map["id"];
     employerName=map["employerName"];
     title=map["title"];
     description=map["description"];
     created=dateFormat.parse(map["created"]);
     deadline=dateFormat.parse(map["dealine"]);
     status=map["status"];
     gender=map["gender"];
     quantity=map["quantity"];
     wageName=map["wageName"];
     categoryName=map["categoryName"];
     localName=map["localName"];
     rankName=map["rankName"];
     typeName=map["typeName"];
     expName=map["expName"];
     employerLogo=map["employerLogo"];
   }
   Map<String,dynamic> toMap(){
     var dateFormat=DateFormat("dd/MM/yyyy");
     return <String,dynamic>{
       "id":id ?? 0,
       "employerName":employerName,
       "title":title,
       "description":description,
       "created":dateFormat.format(created??DateTime.now()),
       "dealine":dateFormat.format(deadline??DateTime.now()),
       "status":status,
       "gender":gender,
       "quantity":quantity,
       "wageName":wageName,
       "categoryName":categoryName,
       "localName":localName,
       "rankName":rankName,
       "typeName":typeName,
       "expName":expName,
       "employerLogo":employerLogo
     };
   }
}