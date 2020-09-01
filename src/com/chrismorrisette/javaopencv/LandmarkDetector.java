package com.chrismorrisette.javaopencv;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfRect;
import org.opencv.core.Scalar;
import org.opencv.face.Face;
import org.opencv.face.Facemark;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class LandmarkDetector {

	private String pathToSourceImage;
	private String pathToClassifier;
	private String pathToFacemarkModel;
	private Mat sourceImage;
	Facemark facemark;
	CascadeClassifier faceDetector;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		if (args.length != 4) {
			throw new RuntimeException(
					"Expecting four arguments: path to source image, path to classifier, path to facemark model, "
							+ "path to where the destination image should be written");
		}

		LandmarkDetector detector = new LandmarkDetector();
		detector.loadSourceImage(args[0]);
		detector.loadClassifier(args[1]);
		detector.loadFacemarkModel(args[2]);
		detector.detectLandmarks(args[3]);
	}

	public void detectLandmarks(String filePath) {
		List<MatOfPoint2f> landmarks = new ArrayList<>();
		MatOfRect faces = new MatOfRect();

		if (faceDetector == null) {
			throw new RuntimeException("Please load classifier before detecting landmarks.");
		}

		if (facemark == null) {
			throw new RuntimeException("Please load facemark model before detecting landmarks.");
		}

		if (sourceImage == null) {
			throw new RuntimeException("Please specify source image before detecting landmarks.");
		}

		System.out
				.println("Detecting faces from image: " + pathToSourceImage + " using classifier: " + pathToClassifier);
		faceDetector.detectMultiScale(sourceImage, faces);
		System.out.println("Faces detected: " + faces.height());

		System.out.println(
				"Detecting faces from image: " + pathToSourceImage + " using facemark model: " + pathToFacemarkModel);
		boolean success = facemark.fit(sourceImage, faces, landmarks);
		if (success) {
			for (int i = 0; i < landmarks.size(); i++) {
				MatOfPoint2f landmarkPoint = landmarks.get(i);
				Face.drawFacemarks(sourceImage, landmarkPoint, new Scalar(0, 0, 255));
			}
		}

		System.out.println("Writing to file: " + filePath);
		boolean writeSuccess = Imgcodecs.imwrite(filePath, sourceImage);
		if (writeSuccess) {
			System.out.println("File succesfully written");
		} else {
			System.out.println("File writing failed");
		}

	}

	public void loadSourceImage(String filePath) {
		pathToSourceImage = filePath;
		sourceImage = Imgcodecs.imread(filePath);
	}

	public void loadClassifier(String filePath) {
		pathToClassifier = filePath;
		faceDetector = new CascadeClassifier(filePath);

	}

	public void loadFacemarkModel(String filePath) {
		pathToFacemarkModel = filePath;
		facemark = Face.createFacemarkLBF();
		facemark.loadModel(filePath);
	}

}
