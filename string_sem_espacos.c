#include <stdio.h>
#include <stdlib.h>

int sizeStr(char *string){
    int a = 0;
    while(*string != '\0'){
        if(*string != ' '){
            a++;
        }
        string++;
    }
    return a;
}

void transformString(char *orig, char *dest){
    while(*orig != '\0'){
        if(*orig != ' '){
            *dest = *orig;
            dest++;
        }
        orig++;
    }
    *dest = '\0';
}

int main(){
    char stringUser[300];

    printf("Digite uma frase: ");
    gets(stringUser);

    //char *string = "String com espacos muito loucos";
    char *newString;

    int size = sizeStr(stringUser);
    newString = (char*)malloc( 1 + size*sizeof(char) );
    char *auxString = newString;

    printf("\nSem espacos: ");
    transformString(stringUser, newString);
    puts(auxString);

    return 0;
}
