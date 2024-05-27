class Employer {
  int? id;
  int? accountID;
  String? accountName;
  String? name;
  String? scale;
  String? logo;
  String? link;
  String? description;
  String? address;
  String? mapLink;
  bool? status;
  String? cover;
  int? follow;

  Employer(
  {this.id,
    this.accountID,
    this.accountName,
    this.name,
    this.scale,
    this.logo,
    this.link,
    this.description,
    this.address,
    this.mapLink,
    this.status,
    this.cover,
    this.follow});
  Employer.fromMap(Map<String,dynamic> map){
    id=map["id"];
    accountID=map["accountID"];
    accountName=map["accountName"];
    name=map["name"];
    scale=map["scale"];
    logo=map["logo"];
    link=map["link"];
    description=map["description"];
    address=map["address"];
    mapLink=map["mapLink"];
    status=map["status"];
    cover=map["cover"];
    follow=map["folow"];
  }
  Map<String,dynamic> toMap(){
    return <String,dynamic>{
      "id":id ?? 0,
      "accountId":accountID,
      "accountName":accountName,
      "name":name,
      "scale":scale,
      "logo":logo,
      "link":link,
      "description":description,
      "address":address,
      "mapLink":mapLink,
      "status":status,
      "cover":cover,
      "follow":follow,
    };
  }
}
