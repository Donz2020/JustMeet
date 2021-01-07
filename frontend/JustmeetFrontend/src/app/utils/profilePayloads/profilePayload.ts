import {changeUserRolePayload} from "./changeUserRolePayload";

export interface profilePayload extends changeUserRolePayload {
  details: string;
  name: string;
}
