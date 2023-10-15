<%@ page contentType="text/html;charset=UTF-8"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用OA系统</title>
    <style>

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 300px;
  margin: auto;
  padding: 20px 30px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f4f4f4;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
  width: 80%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

input[type="submit"] {
  background-color: #4CAF50;
  color: #fff;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #3e8e41;
}
    </style>
</head>
<body>
    <div style="display: flex; justify-content: center; align-items: center; height: 100vh;">
        <form action="<%=request.getContextPath()%>/user/login" method="post">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="登录">
        </form>
    </div>
</body>
</html>