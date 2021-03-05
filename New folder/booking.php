<?php
$rid=$_GET["rid"];
$name=$_GET["name"];
$passno=$_GET["passno"];

$passcountry=$_GET["passcountry"];
$dat=$_GET["dat"];
$extra=$_GET["extra"];

$clas=$_GET["clas"];
$child=$_GET["child"];
$adult=$_GET["adult"];
$total=$_GET["total"];
$uname=$_GET["uname"];

//SELECT `bid`, `rid`, `name`, `passno`, `passcountry`, `dat`, `extra`, `status`, `clas`, `child`, `adult`, `total` FROM `book` WHERE 1
include 'config.php';
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

$query_dis="insert into book(rid,name,passno,passcountry,dat,extra,clas,child,adult,total,status,uname) values('$rid','$name','$passno','$passcountry',
'$dat','$extra','$clas','$child','$adult','$total','Booked','$uname');";

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);


   echo '{"message":"Successfully Booked.","status":"true"}'; 

?>