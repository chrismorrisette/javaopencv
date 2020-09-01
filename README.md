# Introduction
I had trouble finding pure Java examples for integrating with OpenCV, so I wanted to publish a few very simple examples.

# Setup
These simple examples use JDK 14.0.1 and OpenCV 4.4.0

Instructions on OpenCV installation can be found [here](https://docs.opencv.org/master/df/d65/tutorial_table_of_content_introduction.html)

Instructions on how to use OpenCV with Eclipse can be found [here](https://docs.opencv.org/master/d1/d0a/tutorial_java_eclipse.html)

# Usage
Example usage (with standard 4.4.0 installation on a Mac).

Landmark Detection
```
java -cp "./bin/:/usr/local/Cellar/opencv/4.4.0/share/java/opencv4/*" -Djava.library.path="/usr/local/Cellar/opencv/4.4.0/share/java/opencv4/" com.chrismorrisette.javaopencv.LandmarkDetector resources/images/turing.jpg resources/models/lbpcascade_frontalface.xml resources/models/lbfmodel.yaml resources/images/output.jpg
```

# Example Input and Output
Landmark detection

![Einstein Landmark Input](https://raw.githubusercontent.com/chrismorrisette/javaopencv/master/resources/images/einstein.jpg)

![Einstein Landmark Output](https://raw.githubusercontent.com/chrismorrisette/javaopencv/master/resources/images/einstein-output.jpg)
