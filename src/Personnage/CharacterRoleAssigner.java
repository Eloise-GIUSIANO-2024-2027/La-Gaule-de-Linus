package Personnage;

import java.util.Random;

public class CharacterRoleAssigner {
    private static final Random random = new Random();

    public static void assignRole(PNJStats character, CharacterType type) {
        character.setType(type);

        switch (type) {
            case GAULOIS:
                character.setRole(getRandomGauloisRole());
                break;
            case ROMAIN:
                character.setRole(getRandomRomainRole());
                break;
            case CREATURE:
                character.setRole(CharacterRole.LYCANTHROPE);
                break;
        }
    }

    public static void assignRole(PNJStats character, CharacterType type, CharacterRole role) {
        if (!isRoleValidForType(type, role)) {
            throw new IllegalArgumentException("Le rôle " + role + " n'est pas valide pour le type " + type);
        }
        character.setType(type);
        character.setRole(role);
    }

    private static CharacterRole getRandomGauloisRole() {
        CharacterRole[] gauloisRoles = {
            CharacterRole.MARCHAND,
            CharacterRole.AUBERGISTE,
            CharacterRole.FORGERON,
            CharacterRole.DRUIDE
        };
        return gauloisRoles[random.nextInt(gauloisRoles.length)];
    }

    private static CharacterRole getRandomRomainRole() {
        CharacterRole[] romainRoles = {
            CharacterRole.LEGIONNAIRE,
            CharacterRole.PREFET,
            CharacterRole.GENERAL
        };
        return romainRoles[random.nextInt(romainRoles.length)];
    }

    public static boolean isRoleValidForType(CharacterType type, CharacterRole role) {
        return switch (type) {
            case GAULOIS -> role == CharacterRole.MARCHAND ||
                    role == CharacterRole.AUBERGISTE ||
                    role == CharacterRole.FORGERON ||
                    role == CharacterRole.DRUIDE;
            case ROMAIN -> role == CharacterRole.LEGIONNAIRE ||
                    role == CharacterRole.PREFET ||
                    role == CharacterRole.GENERAL;
            case CREATURE -> role == CharacterRole.LYCANTHROPE;
        };
    }
}

