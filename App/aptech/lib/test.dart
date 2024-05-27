import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class Company {
  final String name;
  final String description;
  final String imageUrl;

  Company({required this.name, required this.description, required this.imageUrl});
}

class MyApp extends StatelessWidget {
  final List<Company> companies = [
    Company(
      name: "Company A",
      description: "Description of Company A",
      imageUrl: "https://lh3.googleusercontent.com/drive-viewer/AKGpihbEXRdE3Qsd_OgM7WKnsT8jCJwdOPw7wwWPnuDzfAnrouMIqxzitr7-vxuKpTvWdQF3ODLsqFSQFX0mLQpqaDTp2nw-s4ZA_jY=w1920-h956",
    ),
    Company(
      name: "Company B",
      description: "Description of Company B",
      imageUrl: "https://lh3.googleusercontent.com/drive-viewer/AKGpihbEXRdE3Qsd_OgM7WKnsT8jCJwdOPw7wwWPnuDzfAnrouMIqxzitr7-vxuKpTvWdQF3ODLsqFSQFX0mLQpqaDTp2nw-s4ZA_jY=w1920-h956",
    ),
    Company(
      name: "Company A",
      description: "Description of Company A",
      imageUrl: "https://lh3.googleusercontent.com/drive-viewer/AKGpihbEXRdE3Qsd_OgM7WKnsT8jCJwdOPw7wwWPnuDzfAnrouMIqxzitr7-vxuKpTvWdQF3ODLsqFSQFX0mLQpqaDTp2nw-s4ZA_jY=w1920-h956",
    ),
    Company(
      name: "Company B",
      description: "Description of Company B",
      imageUrl: "https://lh3.googleusercontent.com/drive-viewer/AKGpihbEXRdE3Qsd_OgM7WKnsT8jCJwdOPw7wwWPnuDzfAnrouMIqxzitr7-vxuKpTvWdQF3ODLsqFSQFX0mLQpqaDTp2nw-s4ZA_jY=w1920-h956",
    ),
    // Add more companies as needed
  ];

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Company Profiles'),
        ),
        body: GridView.count(
          crossAxisCount: 2,
          children: List.generate(
            companies.length,
                (index) {
              return CompanyCard(company: companies[index]);
            },
          ),
        ),
      ),
    );
  }
}

class CompanyCard extends StatelessWidget {
  final Company company;

  CompanyCard({required this.company});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Image.network(
            company.imageUrl,
            width: 100,
            height: 100,
          ),
          SizedBox(height: 8),
          Text(
            company.name,
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 4),
          Text(
            company.description,
            textAlign: TextAlign.center,
          ),
        ],
      ),
    );
  }
}