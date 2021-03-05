<?php

            include 'config.php';
            
    //SELECT `Id`, `firstname`, `lastname`, `email`, `phone`, `pass` FROM `customer` WHERE 1        
$email=$_GET["email"];
$pwd="";
               $con=mysqli_connect($hostname, $username, $password,$dbname);
           
            $query_json = "SELECT * from customer where email='$email';";
            
          //  echo $query_json;
            $query_pwd = "SELECT pass from customer where email='$email';";
            $result_pwd = mysqli_query($con,$query_pwd);
            $result = mysqli_query($con,$query_json);
            while($row = mysqli_fetch_assoc($result_pwd)) {
                $pwd=$row['pass'];
               // echo $pwd;
            }
       
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
                echo '{"message":"Sorry Invalid Email/Username","status":"false"}';
            }else{
            
 $to = $email;
     
         $subject = "Password Request";
         
         $message = "Your password is <b>$pwd</b>";
         
         
         $header = "From:sushmitha555reddy@gmail.com \r\n";
        
         $header .= "MIME-Version: 1.0\r\n";
         $header .= "Content-type: text/html\r\n";
         
         $retval = mail ($to,$subject,$message,$header);
         
      //  echo $retval;
                echo '{"message":"Password sent to your register email successfully.","status":"true"}';
            }
?>