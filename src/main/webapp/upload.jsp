<%--
  Created by IntelliJ IDEA.
  User: minhthu
  Date: 6/23/21
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UploadFile</title>
</head>
<body>
    <h1>Demo upload file</h1>
    <form action="api/v1/upload-file" method="post" enctype="multipart/form-data">
        <label>Chon file</label>
        <input type="file" name="file" multiple="multiple">
        <input type="submit" value="Submit">
    </form>

</body>
</html>
