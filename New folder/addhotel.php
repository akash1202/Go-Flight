<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
//server granting permisions line
include 'config.php';
 
//SELECT `hid`, `photo`, `name`, `country`, `city`, `province`, `pcode`, `price` FROM `hotel` WHERE 1
$name=$_POST["name"];
$country=$_POST["country"];
$city=$_POST["city"];
$province=$_POST["province"];
$pcode=$_POST["pcode"];
$price=$_POST["price"];

//$pic=trim($pic,'"');

$result = array("success" => $_FILES["file"]["name"]);
$file_path = basename( $_FILES['file']['name']);
$picimg_url='images/'.$file_path;
if(move_uploaded_file($_FILES['file']['tmp_name'], 'images/'.$file_path)) {
    $result = array("success" => "File successfully uploaded");
} else{
    $result = array("success" => "error uploading file");
}

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json = "SELECT * from hotel name='$name';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
               
$query_dis="insert into hotel(name,country,city,province,pcode,price,web,photo) values($name,$country,$city,$province,$pcode,$price,'https://mercure.accor.com/gb/canada/index.shtml','$picimg_url');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Hotel Added successfully.","status":"true"}';
}else{
   echo '{"message":"Hotel is already added.","status":"false"}'; 
}
?>