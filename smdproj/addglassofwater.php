<?php
#   http://localhost/smdass3/insert.php
include "conn.php";
$res=array();
if(isset($_POST['Username'],$_POST['Password'],$_POST['Glass'],$_POST['Gender']))
{
    $name=$_POST['Username'];
   
    $password=$_POST['Password'];
    $glass=$_POST['Glass'];
   
    $gender=$_POST['Gender'];

    $query="INSERT INTO `waterintake`
    (`username`, `password`, `glass`,`Gender`) 
    VALUES ('$name','$password','$glass','$gender')";
    $r=mysqli_query($con,$query);

    if($r)
    {
        $res["msg"]="Water Intake Inserted Sucessfully";
        $res["code"]=1;
        $res["id"]=mysqli_insert_id($con);
    }
    else{
        $res["msg"]="Failed to Insert";
        $res["resultcode"]=-1;
        $res["id"]="N/A";

    }
}
else{
    $res["msg"]="incomplete Request";
    $res["resultcode"]=-1;
    $res["id"]="N/A";


}

$x=json_encode($res);
echo $x;
?>