
# lon=input("Enter the long value ")
# lat= input("Enter the lat value ")
# lonlat={"long":float(lon),"late":float(lat)}

# print(lonlat)
# Eplace=input("enter the place:")
# place={Eplace:lonlat}
# print(place)
nameList=[]
filePath=r"/Users/dineshkolla/Desktop/Masters Back_up/Master's/AI/Assignment 1/hw1-astar/hw1-astar/france-latlong.txt"
# with open(filePath) as file:
#      lines = [line.strip() for line in file]
#      print(lines)

with open(filePath) as file:
     for line in file:
         txt=(line.strip())
         txts=[]
         # print(txt)
         if not txt.isspace():
            if(txt.startswith("#")):
                continue
            else:
                txt=txt.split(",")
                print(str(txt))
            #  nameList.append(txts)
        # txt=line.rstrip().
# with open(filePath) as file1:
#     print("====>")
#     txt=file1.readlines()
#     print(type(txt))
#     # lines = file1.readlines()
#     # print("====>")
#     # print(lines)
#     #  txt=(file1.read())
# #  print(nameList)

    
