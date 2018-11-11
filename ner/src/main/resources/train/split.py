lines_per_file = 1000
smallfile = None
num = 0
with open('/home/atig/Data/github/named-entity-recognition/ner/src/main/resources/train/TrainNER.csv') as bigfile:
    for lineno, line in enumerate(bigfile):
        if lineno % lines_per_file == 0:
            if smallfile:
                smallfile.close()
            num+=1
            small_filename = 'train{}.data'.format(num)
            smallfile = open(small_filename, "w")
        smallfile.write(line)
    if smallfile:
        smallfile.close()