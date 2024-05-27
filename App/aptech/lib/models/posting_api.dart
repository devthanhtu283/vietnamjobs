import 'dart:convert';

import 'package:aptech/entities/posting.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class PostingAPI {
  Future<List<Posting>>findAll() async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"posting/findAll"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Posting.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
  Future<List<Posting>>findByEmployerID(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"posting/findByEmployerID/${id}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Posting.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
  Future<List<Posting>>findByEmployerName(String name) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"posting/findByEmployerName/${name}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Posting.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
  Future<List<Posting>>findByTitle(String name) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"posting/findbyemployertitle/${name}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Posting.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
  Future<Posting>findById(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"posting/findById/${id}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      dynamic result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return Posting.fromMap(result);
    }else{
      throw Exception("Bad request");
    }
  }
}