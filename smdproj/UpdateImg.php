<?php
include "conn.php";

if(isset($_POST['image'],$_POST['username'],$_POST['password'])){


	
	$img=$_POST['image'];
    $username=$_POST['username'];
    $password=$_POST['password'];
	

	$filename="IMG".rand().time().".jpg";
    file_put_contents("Dps/".$filename,base64_decode($img));

    $qry="UPDATE users SET  image = '$filename' WHERE username = '$username' and password ='$password' ";

    $result = mysqli_query($con,$qry);
    
     
    if($result){
        echo "Dp Updated";
       
    }
    else{
        echo "Failed";
    }


}

else{
    $result["msg"]="incomplete Request";
    $result["resultcode"]=-1;
    $result["id"]="N/A";


}

?>
