import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:it_unicam_cs_ids_justmeet_frontend/config/User.dart';


class home extends StatefulWidget {
  home({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<home> {


  @override
  Widget build(BuildContext context) {
    final User args = ModalRoute.of(context).settings.arguments;
    return Scaffold(
        appBar: AppBar(
          title: Text("Home"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              //Text("Welcome back " + args.username + "!"),
              /*RaisedButton(
                onPressed: _handleLogout,
                child: Text("Logout"),
              )*/
            ],
          ),
        ));
  }
}