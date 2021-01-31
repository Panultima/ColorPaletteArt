# ColorPaletteArt

Some simple fun with steganography - embedding files inside images and then recovering said files. This code was written from scratch for personal enjoyment - do not misuse or use in sensitive contexts.  

Currently there are two main modes - 'rnd' and 'lsb'. 'rnd' encrypts the bytes of the file and pads it so it is a perfect square. It then writes it as a png image of colors associated with the encrypted values. This function uses the 'PNGSimple' class.

'lsb' does the traditional embedding inside of the last bit of every byte of the image. This function uses the 'PNG' class. 
Even though it writes the data as a 'png', if you pass it an output file name such as 'myjpg.jpg' it will save it as a jpg,
but process it like a png (you avoid the lossy problem). All that to say, you can use this method on JPEGs, it just kind of
fakes it by using LSB like with a png and storing it as a jpg. 

Both modes use a key image for encryption and use AES 256 bit symmetric key encryption. 

If you do not enter in a valid # of arguments the program automatically produces a randomly sized random color palette png. This is its 'disguise'. May want to change this to require a password or key as an additional argument.

#### Usage

Below are the commands to embed/recover in both the 'rd' and 'lsb' modes. For 'lsb', there is an extra argument when embedding (Forest.png) which is the name of the image file that you want to embed the data in.  

- java -cp [classpath] Main rd e files/idea_checklist.pdf files/keyimage.jpg out.png
- java -cp [classpath] Main rd r out.png files/keyimage.jpg out.pdf (make sure file extension is correct here)
- java -cp [classpath] Main lsb e files/idea_checklist.pdf files/keyimage.jpg files/Forest.png out.png
- java -cp [classpath] Main lsb r out.png files/keyimage.jpg out.pdf (make sure file extension is correct here)

You can also use 'java -jar ColorPaletteArt.jar' in place of 'java -cp [classpath] Main'

#### Possible Next Steps

- Create a true JPG class that uses DCT (Discrete Cosine Transform) coefficients for embedding the data
- Perhaps add a hash of the encrypted data into the embedded data so that it can be verified upon retrieval
