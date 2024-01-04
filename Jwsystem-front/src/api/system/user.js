import request from "@/utils/request";
import { encrypt } from "@/utils/rsaEncrypt";

export function add(data) {
  return request({
    url: "api/user",
    method: "post",
    data
  });
}

export function del(ids) {
  return request({
    url: "api/user",
    method: "delete",
    data: ids
  });
}

export function edit(data) {
  return request({
    url: "api/user",
    method: "put",
    data
  });
}

export function editUser(data) {
  return request({
    url: "api/user/edit",
    method: "put",
    data
  });
}

export function updatePass(user) {
  const data = {
    oldPass: user.oldPass,
    newPass: user.newPass
  };
  return request({
    url: "api/user/updatePass",
    method: "post",
    data
  });
}

export function updateEmail(form) {
  const data = {
    password: encrypt(form.pass),
    email: form.email
  };
  return request({
    url: "api/user/updateEmail/" + form.code,
    method: "post",
    data
  });
}

export default { add, edit, del };
