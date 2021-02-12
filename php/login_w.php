
<?php
include "db.php"; 
$response = array();
if($_POST['email'] && $_POST['password']){
	$email = $_POST['email'];
	$post_password = $_POST['password'];
	$stmt = $conn->prepare("SELECT fullname, password FROM user_worker WHERE mobileno = ?");
	$stmt->bind_param("s",$email);
	$stmt->execute();
	$stmt->bind_result($username, $db_password);
	$stmt->fetch();
	if(password_verify($post_password, $db_password)){
		$response['error'] = false;
		$response['message'] = "Login Successful!";
		$response['email'] = $email;
		$response['username'] = $username;
		//echo "hello";
	} else{
		$response['error'] = false;
		$response['message'] = "Invalid Email or Password";
		//echo "not hello";
	}
} else {
	$response['error'] = true;
	$response['message'] = "Insufficient Parameters";
}
echo json_encode($response);	
?>