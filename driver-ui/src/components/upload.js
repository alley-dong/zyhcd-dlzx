import { minioChooseBucketUpload } from "@/api/api";

export function upload(file, detail) {
    let fd = new FormData();
    return new Promise(function (resolve, reject) {
        fd.append("file", file.file); // 要提交给后台的文件
        minioChooseBucketUpload(fd)
            .then((response) => {
                resolve(response.msg)
            })
            .catch((err) => {
                reject(err)
            });
    })
}