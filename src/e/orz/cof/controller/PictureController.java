package e.orz.cof.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import e.orz.cof.util.IdGenerator;

@Controller
public class PictureController {

	// �ϴ�ͼƬ�ļ�
	@RequestMapping(value = "/upload.do")
	public void uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dff);
			List<FileItem> items = sfu.parseRequest(request);
			// ��ȡ�ϴ��ֶ�
			FileItem fileItem = items.get(0);
			// �����ļ���ΪΨһ��
			String filename = fileItem.getName();
			if (filename != null) {
				filename = IdGenerator.generateGUID() + "." + FilenameUtils.getExtension(filename);
			}
			// ���ɴ洢·��
			String storeDirectory = request.getSession().getServletContext().getRealPath("/files/images");
			File file = new File(storeDirectory);
			if (!file.exists()) {
				file.mkdir();
			}
			String path = IdGenerator.genericPath(filename, storeDirectory);
			// �����ļ����ϴ�
			try {
				fileItem.write(new File(storeDirectory + path, filename));

				String filePath = "/files/images" + path + "/" + filename;
				message = filePath;
			} catch (Exception e) {
				e.printStackTrace();
				message = "�ϴ�ͼƬʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "�ϴ�ͼƬʧ��";
		} finally {
			System.out.println(message);
			response.getWriter().write(message);
		}
	}

}
