import 'dart:convert';
import 'package:aptech/entities/follow.dart';
import 'package:aptech/entities/follow_db.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class FollowAPI{
  Future<List<Follow>>findBySeekerId(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"follow/findBySeekerId/${id}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Follow.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
  Future<Follow>findBySeekerId1(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"follow/findBySeekerId1/${id}"));
    if(response.statusCode ==200){
//ÉP NGƯỢC LẠI THÀNH ACCOUNT
      dynamic result= jsonDecode(response.body);
      return Follow.fromMap(result);
    }else{
      throw Exception("Bad request");
    }
  }
  Future<List<FollowDB>> listPostFollowBySeekerID(int id) async {
    // Build the URL and make the GET request
    final response = await http.get(Uri.parse('${BareUrl.url}follow/listPostFollowBySeekerID/${id}'));

    // Check the response status code
    if (response.statusCode == 200) {
      // Decode the JSON response into a list of dynamic objects
      List<dynamic> result = jsonDecode(response.body);

      // Convert the list of dynamic objects into a list of FollowDB objects and return it
      return result.map((dyn) => FollowDB.fromMap(dyn)).toList();
    } else {
      // Throw an exception if the response status code is not 200
      throw Exception("Failed to load follows: ${response.statusCode}");
    }
  }

  Future<List<Follow>>listPostFollowBySeekerID1(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"follow/listPostFollowBySeekerID/${id}"));
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      List<dynamic> result= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ
      return result.map((dyn)=>Follow.fromMap(dyn)).toList();
    }else{
      throw Exception("Bad request");
    }
  }
}