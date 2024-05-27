import 'dart:convert';

import 'package:aptech/entities/account.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class AccountAPI{
  Future<bool>login(Account account) async{
    var response= await http.post(Uri.parse(BareUrl.url+"account/login"),
      body: json.encode(account.toMap()),
      headers: {
        "Content-Type":"application/json",
      },
    );
    print(response.body);
    if(response.statusCode == 200){
      dynamic dyn= jsonDecode(response.body);
      return dyn["status"];
    }else{
      throw Exception("Bad request");
    }
  }
  Future<Account>findByUsername(String username) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"account/findByUsername/${username}"));
    if(response.statusCode ==200){
//ÉP NGƯỢC LẠI THÀNH ACCOUNT
      dynamic result= jsonDecode(response.body);
      return Account.fromMap(result);
    }else{
      throw Exception("Bad request");
    }
  }
  Future<Account>findById(int id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"account/findById/${id}"));
    if(response.statusCode ==200){
//ÉP NGƯỢC LẠI THÀNH ACCOUNT
      dynamic result= jsonDecode(response.body);
      return Account.fromMap(result);
    }else{
      throw Exception("Bad request");
    }
  }
  Future<bool>update(Account account) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.put(Uri.parse(BareUrl.url+"account/update"),
      //BIẾN CATEGORY THÀNH JSON ĐẨY LÊN
      body: json.encode(account.toMap()),
      headers: {
        //DỮ LIỆU TRUYỀN LÊN KIỂU JSON
        "Content-Type":"application/json",
      },
    );
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      dynamic dyn= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ CHỈ CÓ 1 NÊN TRẢ TRỰC TIOE61 ĐỐI TƯỢNG
      return dyn["result"];
    }else{
      throw Exception("Bad request");
    }
  }
  Future<bool>create(Account account) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.post(Uri.parse(BareUrl.url+"account/create"),
      //BIẾN CATEGORY THÀNH JSON ĐẨY LÊN
      body: json.encode(account.toMap()),
      headers: {
        //DỮ LIỆU TRUYỀN LÊN KIỂU JSON
        "Content-Type":"application/json",
      },
    );
    if(response.statusCode ==200){
      //BIẾN JOSN THÀNH KIỂU DANH SÁCH
      dynamic dyn= jsonDecode(response.body);
      //TRẢ VỀ KẾT QUÁ CHỈ CÓ 1 NÊN TRẢ TRỰC TIOE61 ĐỐI TƯỢNG
      return dyn["result"];
    }else{
      throw Exception("Bad request");
    }
  }
}