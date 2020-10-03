import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:it_unicam_cs_ids_justmeet_frontend/config/ApiError.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/config/ApiResponse.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/config/User.dart';
import '../main.dart';
import 'dart:convert' show json, base64, ascii;
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

final storage = FlutterSecureStorage();


class login extends StatefulWidget {
  final String title;

  login({Key key, this.title}) : super(key: key);

  @override
  State<StatefulWidget> createState() => new _loginState();
}

Future<ApiResponse> attemptLogIn(String username, String password) async {

  ApiResponse _apiResponse = new ApiResponse();

  try {
    final res = await http.post(
        "$SERVER_IP/api/auth/login",
        headers: {"Content-Type": "application/json"},
        body: ({
          "username": username,
          "password": password
        }));
        switch (res.statusCode){

          case 200:
            // var usr = new User(username,password)
             _apiResponse.Data = User.fromJson(json.decode(res.body));
            break;
           case 401:
             _apiResponse.ApiError = ApiError.fromJson(json.decode(res.body));
            break;
            default:
             _apiResponse.ApiError = ApiError.fromJson(json.decode(res.body));
            break;

    }
  } on SocketException {
  _apiResponse.ApiError = ApiError(error: "Server error. Please retry");
  }
  return
  _apiResponse;
}


class _loginState extends State<login> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  String get username => username;
  String get password => password;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //title: "JustMeet Login",
        appBar: AppBar(
          title: Text('Login'),
        ),
        body: Padding(
            padding: EdgeInsets.all(10),
            child: ListView(
              children: <Widget>[
                Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(10),
                    child: Text(
                      'JustMeet Login',
                      style: TextStyle(
                          color: Colors.blue,
                          fontWeight: FontWeight.w500,
                          fontSize: 30),
                    )),
                Container(
                  padding: EdgeInsets.all(10),
                  child: TextField(
                    controller: usernameController,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'User Name',
                    ),
                  ),
                ),
                Container(
                  padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                  child: TextField(
                    obscureText: true,
                    controller: passwordController,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Password',
                    ),
                  ),
                ),
                FlatButton(
                  onPressed: () {
                    //forgot password screen
                  },
                  textColor: Colors.blue,
                  child: Text('Forgot Password'),
                ),
                Container(
                    height: 50,
                    padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
                    child: RaisedButton(
                      textColor: Colors.white,
                      color: Colors.blue,
                      child: Text('Login'),
                      onPressed: () async {
                        attemptLogIn(username, password);
                      }),
                    ),
                Container(
                    child: Row(
                      children: <Widget>[
                        Text('Does not have account?'),
                        FlatButton(
                          textColor: Colors.blue,
                          child: Text(
                            'Register',
                            style: TextStyle(fontSize: 20),
                          ),
                          onPressed: () {
                            Navigator.pushNamed(context, '/register');
                          },
                        )
                      ],
                      mainAxisAlignment: MainAxisAlignment.center,
                    ))
              ],
            )));
  }
}
