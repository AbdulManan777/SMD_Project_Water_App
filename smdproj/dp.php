<?php
include "conn.php";

if(isset($_POST['Username'],$_POST['Password'],$_POST['PhoneNum'],$_POST['image'])){


	
	$img=$_POST['image'];
	$usern=$_POST['Username'];
    $pass=$_POST['Password'];
    $phone=$_POST['PhoneNum'];

	$filename="IMG".rand().time().".jpg";
    file_put_contents("Dps/".$filename,base64_decode($img));

$qry="INSERT INTO `users` (`username`,`password`,`PhoneNum`,`image`)
   VALUES ('$usern','$pass','$phone','$filename')";

$res=mysqli_query($con,$qry);

if($res==true)
echo "User with Dp Added succesfully";
else
echo "Could not upload File";
}

else{

	$res["msg"]="incomplete Request";
    $res["resultcode"]=-1;
    $res["id"]="N/A";

}
      
?>
