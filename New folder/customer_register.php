<?php
$fname=$_GET["fname"];
$lname=$_GET["lname"];
$email=$_GET["email"];
$pass=$_GET["pass"];
//SELECT `Id`, `firstname`, `lastname`, `email`, `phone`, `pass` FROM `customer` WHERE 1
$phone=$_GET["phone"];
include 'config.php';
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json = "SELECT * from customer where uname='$uname';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
$query_dis="insert into customer(firstname,lastname,email,phone,pass) values('$fname','$lname','$email','$phone','$pass');";

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"User Registered successfully.","status":"true"}';
}else{
   echo '{"message":"UserName is already exist.","status":"false"}'; 
}
?>