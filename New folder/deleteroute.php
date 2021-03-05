<?php

     include 'config.php';
     
     $rid=$_GET['rid'];
     
            $con=mysqli_connect($hostname, $username, $password,$dbname);
            mysqli_query ($con,"set character_set_results='utf8'");
// sql to delete a record
$sql = "DELETE FROM routes WHERE rid='$rid'";

if (mysqli_query($con, $sql)) {
  echo '{"message":"Record deleted successfully","status":"true"}' ;
} else {
  echo '{"message":"Record deletion failed","status":"false"}' ;
}

mysqli_close($con);
?>