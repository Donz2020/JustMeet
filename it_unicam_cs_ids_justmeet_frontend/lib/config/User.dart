import 'dart:core';

class User {

  String _username;
  String _password;

  // constructorUser(
User (String username, String password) {
this._username = username;
this._password = password;
}

// Properties

String get username => _username;
set username(String username) => _username = username;
String get email => _password;
set email(String email) => _password = email;

// create the user object from json input
User.fromJson(Map<String, dynamic> json) {

_username = json['username'];
_password = json['email'];
}

// exports to json
Map<String, dynamic> toJson() {
  final Map<String, dynamic> data = new Map<String, dynamic>();

  data['username'] = this._username;
  data['email'] = this._password;
  return data;
}
}