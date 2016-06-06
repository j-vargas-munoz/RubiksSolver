# RubiksSolver

**Developed by**

José de Jesús Vargas Muñoz			[j-vargas-munoz](https://github.com/j-vargas-munoz)
Luis Alfredo Lizárraga Santos		[luislizarraga](https://github.com/luislizarraga)
			

### Introduction

Desktop application that is able to read the current state of a 3x3 Rubik's Cube and suggest a set of steps to solve it. The cube is read through real time video making use of OpenCV, from which all 6 faces must be captured. After the reading stage, the cube configuration is rendered with the aid of Processing, which will show the solution to the user.

### Bilateral Filter

A bilateral filter is a non linear smoothing filter, which preserves edges and reduces noise. In an image, the intensity value at each pixel is replaced by a pondered average of the intensity of the values of nearby pixels. This weight can be based in a Gaussian distribution. The weights depend not only on the Euclidean distance between pixels, but also on the color intensity and depth difference. Borders are preserved iterating over each pixel and adjusting its weight according to the weights of adjacent pixels.

The Bilateral Filter is definded as

<img src="http://www.sciweavers.org/tex2img.php?eq=%24I%5E%7Bfiltered%7D%28x%29%20%3D%20%7B1%20%5Cover%20W_p%7D%20%5Csum%5Climits_%7Bx_i%20%5Cin%20%5COmega%7D%20I%28x_i%29f_r%28%5C%7CI%28x_i%29%20-%20I%28x%29%5C%7C%29g_s%28%5C%7Cx_i%20-%20x%5C%7C%29%24&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0" align="center" border="0" alt="$I^{filtered}(x) = {1 \over W_p} \sum\limits_{x_i \in \Omega} I(x_i)f_r(\|I(x_i) - I(x)\|)g_s(\|x_i - x\|)$" width="426" height="37" />

where the normalization term is

<img src="http://www.sciweavers.org/tex2img.php?eq=%24W_p%20%3D%20%5Csum%5Climits_%7Bx_i%20%5Cin%20%5COmega%7D%20f_r%28%5C%7CI%28x_i%29%20-%20I%28x%29%5C%7C%29g_s%28%5C%7Cx_i%20-%20x%5C%7C%29%24&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0" align="center" border="0" alt="$W_p = \sum\limits_{x_i \in \Omega} f_r(\|I(x_i) - I(x)\|)g_s(\|x_i - x\|)$" width="311" height="35" />

and

1. <img src="http://www.sciweavers.org/tex2img.php?eq=%24I%5E%7Bfiltered%7D%24&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0" align="center" border="0" alt="$I^{filtered}$" width="60" height="18" /> is 
the filtered image, 
2. <img src="http://bit.ly/1UlCNRR" align="center" border="0" alt="$I$" width="8" height="14" /> is the original input image to be filtered,
3. <img src="http://bit.ly/1Ya8TWp" align="center" border="0" alt="$x$" width="12" height="12" /> represents the coordinates of the pixel being filtered
4. <img src="http://bit.ly/1Ya9lEd" align="center" border="0" alt="$\Omega$" width="15" height="15" /> is the window centered in <img src="http://bit.ly/1Ya8TWp" align="center" border="0" alt="$x$" width="12" height="12" />,
5. <img src="http://bit.ly/1Ya9o2R" align="center" border="0" alt="$f_r$" width="15" height="18" /> represents the kernel used to smooth intensity differences. This function may be gaussian.
6. <img src="http://bit.ly/1Ya9SFW" align="center" border="0" alt="$g_s$" width="21" height="17" /> represents the kernel used to smooth coordinate differences. This function may be gaussian.

