# RubiksSolver

**Developed by**

| José de Jesús Vargas Muñoz			[j-vargas-munoz](https://github.com/j-vargas-munoz)
| Luis Alfredo Lizárraga Santos		[luislizarraga](https://github.com/luislizarraga)
			

### Introduction

Desktop application that is able to read the current state of a 3x3 Rubik's Cube and suggest a set of steps to solve it. The cube is read through real time video making use of OpenCV, from which all 6 faces must be captured. After the reading stage, the cube configuration is rendered with the aid of Processing, which will show the solution to the user.

### Bilateral Filter

A bilateral filter is a non linear smoothing filter, which preserves edges and reduces noise. In an image, the intensity value at each pixel is replaced by a pondered average of the intensity of the values of nearby pixels. This weight can be based in a Gaussian distribution. The weights depend not only on the Euclidean distance between pixels, but also on the color intensity and depth difference. Borders are preserved iterating over each pixel and adjusting its weight according to the weights of adjacent pixels.

The Bilateral Filter is definded as

$I^{filtered}(x) = {1 \over W_p} \sum\limits_{x_i \in \Omega} I(x_i)f_r(\|I(x_i) - I(x)\|)g_s(\|x_i - x\|)$

where the normalization term is

$W_p = \sum\limits_{x_i \in \Omega} f_r(\|I(x_i) - I(x)\|)g_s(\|x_i - x\|)$

and

1. $I^{filtered}$ is the filtered image, 
2. $I$ is the original input image to be filtered,
3. $x$ represents the coordinates of the pixel being filtered,
4. $\Omega$ is the window centered in $x$,
5. $f_r$ represents the kernel used to smooth intensity differences. This function may be gaussian.
6. $g_s$ represents the kernel used to smooth coordinate differences. This function may be gaussian.

