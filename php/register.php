<?php
 
//getting user values
$username=$_POST['username'];
$email=$_POST['email'];
$password=$_POST['password'];
 
//array of responses
$output=array();
 
//require database
require_once('db.php');
 
//checking if email exists
$conn=$dbh->prepare('SELECT useremail FROM users WHERE useremail=?');
$conn->bindParam(1,$email);
$conn->execute();
 
//results
if($conn->rowCount() !==0){
$output['error'] = true;
$output['message'] = "Email Exists Please Login";
}else{
 
$conn=$dbh->prepare('INSERT INTO users(username,useremail,password) VALUES (?,?,?)');
//encrypting the password
$pass=md5($password);
$conn->bindParam(1,$username);
$conn->bindParam(2,$email);
$conn->bindParam(3,$pass);
 
$conn->execute();
if($conn->rowCount() == 0)
{
$output['error'] = true;
$output['message'] = "Registration failed, Please try again";
}
elseif($conn->rowCount() !==0){
$output['error'] = false;
$output['message'] = "Succefully Registered";
$output['username']=$username;
}
}
echo json_encode($output);
 
?>