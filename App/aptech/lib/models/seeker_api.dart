import 'dart:convert';
import 'package:aptech/entities/seeker.dart';
import 'package:aptech/models/base_url.dart';
import 'package:http/http.dart' as http;
class SeekerAPI{
  Future<Seeker>findByAccountID(int account_id) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.get(Uri.parse(BareUrl.url+"seeker/findByAccountID/${account_id}"));
    if(response.statusCode ==200){
//ÉP NGƯỢC LẠI THÀNH ACCOUNT
      dynamic result= jsonDecode(response.body);
      return Seeker.fromMap(result);
    }else{
      throw Exception("Bad request");
    }
  }
  Future<bool>update(Seeker seeker) async{
    //NỐI CHUỖI VÀ ĐỌC ĐƯỜNG DẪN TRONG API
    var response= await http.put(Uri.parse(BareUrl.url+"seeker/update"),
      //BIẾN CATEGORY THÀNH JSON ĐẨY LÊN
      body: json.encode(seeker.toMap()),
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
