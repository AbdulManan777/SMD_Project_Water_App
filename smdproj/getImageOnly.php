<?php
include "conn.php";
$response=array();

//$response['data']=array();

if(isset($_POST['Username'],$_POST['Password'])){

    $user=$_POST['Username'];
    $pass=$_POST['Password'];

    $query= "SELECT `image` FROM `users`  WHERE `username` = '$user' and `password` = '$pass' ";

$res=mysqli_query($con,$query);


if($res)
{
    
    $response['msg']="Img retrived";
    $response['code']=1;     
    $response['pictures']=array();
    while($row=mysqli_fetch_array($res))
    {
        $contact=array();
      //  $contact['id']=$row['id'];
        $contact['image']=$row['image'];
       // $contact['Username']=$row['Username'];
        //$contact['Password']=$row['Password'];
        array_push($response['pictures'],$contact);


    }







 
    }
    
    else{

        $response['msg']="Cannot retrive data";
        $response['code']=-1;
    }
}

else{
    $result["msg"]="incomplete Request";
    $result["resultcode"]=-1;
    $result["id"]="N/A";


}
    
    echo json_encode($response);

?>