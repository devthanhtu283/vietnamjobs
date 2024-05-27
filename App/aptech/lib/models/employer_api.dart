import 'dart:convert';
import 'package:aptech/entities/employer.dart';
import 'package:aptech/entities/follow.dart';
import 'package:aptech/entities/follow_db.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class EmployerAPI{
  Future<List<Employer>>findAll() async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"employer/findAll"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Employer.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }

}