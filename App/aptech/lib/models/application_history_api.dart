import 'dart:convert';
import 'package:aptech/entities/application_history.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class ApplicationHistoryAPI{
  Future<bool>create(ApplicationHistory applicationHistory) async{
    var response= await http.post(Uri.parse(BareUrl.url+"applicationHistory/create"),
      body: json.encode(applicationHistory.toMap()),
      headers: {
        "Content-Type":"application/json",
      },
    );
    print(response.body);
    if(response.statusCode == 200){
      dynamic dyn= jsonDecode(response.body);
      return dyn["result"];
    }else{
      throw Exception("Bad request");
    }
  }
  Future<List<ApplicationHistory>>findBySeekerID1(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"applicationHistory/findBySeekerID1/${id}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>ApplicationHistory.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }

}