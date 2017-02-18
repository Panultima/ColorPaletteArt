# ColorPaletteArt

So, currently there are two main modes - 'rnd' and 'lsb'. 

'rnd' encrypts the bytes of the file and pads it so it is a perfect square. It then raws it as a png image of colors
associated with the encrypted values. This function uses the 'PNGSimple' class.

'lsb' does the traditional embedding inside of the last bit of every byte of the image. This function uses the 'PNG' class. 
Even though it writes the data as a 'png', if you pass it an output file name such as 'myjpg.jpg' it will save it as a jpg,
but process it like a png (you avoid the lossy problem). All that to say, you can use this method on JPEGs, it just kind of
fakes it by using LSB like with a png and storing it as a jpg. 

Both modes use a key image for encryption and use AES 256 bit symmetric key encryption. 

# Next Steps

Need to create a true JPG class that uses DCT (Discrete Cosine Transform) coefficients for embedding the data
