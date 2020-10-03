import 'package:flutter/material.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/pages/home.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/pages/login.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/pages/register.dart';
import 'dart:convert' show json, base64, ascii;

const SERVER_IP = 'http://localhost:8080';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login Demo',
      routes: {
        '/': (context) => home(),
        '/login': (context) => login(),
        '/register': (context) => register(title: 'Register'),
      },
      theme: ThemeData(
        primarySwatch: Colors.deepOrange,
      ),
    );
  }
}